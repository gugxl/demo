package com.gugu.demo.util.redis;

import java.util.Map;
import java.util.StringJoiner;
import redis.clients.jedis.params.SetParams;

/**
 * @author Administrator
 * @Classname RedisTaskStore
 * @Description TODO
 * @Date 2021/11/21 12:36
 */
public class RedisTaskStore implements ITaskStore {
    private final static int DEFAULT_LOCK_AGE = 5;

    private RedisStore redis;

    // 组名（任务空间）
    private String group;
    // 多进程争抢任务时使用 Redis 锁（到点只有一个进程可以运行，每个任务一把锁） lockAge 为 锁保持的时间，持有时间内其它进程不得运行该任务
    private int lockAge = DEFAULT_LOCK_AGE;

    public RedisTaskStore(RedisStore redis, String group) {
        this(redis, group, DEFAULT_LOCK_AGE);
    }

    public RedisTaskStore(RedisStore redis, String group, int lockAge) {
        this.redis = redis;
        this.group = group;
        this.lockAge = lockAge;
    }

    @Override
    public long getRemoteVersion() {
        Holder<Long> holder = new Holder<>();
        this.redis.execute(jedis ->  {
            String versionKey = keyFor("version");
            Long remoteVersion = jedis.incrBy(versionKey, 0);
            holder.value(remoteVersion);
        });
        return holder.value();
    }

    private String keyFor(Object... args){
        StringJoiner stringJoiner = new StringJoiner("_");
        stringJoiner.add(group);
        for (Object arg : args) {
            stringJoiner.add(String.valueOf(arg));
        }
        return stringJoiner.toString();
    }

    @Override
    public Map<String, String> getAllTriggers() {
        Holder<Map<String, String>> holder = new Holder<>();
        this.redis.execute( jedis -> {
            String tasksKey = keyFor("triggers");
            holder.value(jedis.hgetAll(tasksKey));

        });
        return holder.value();
    }

    @Override
    public void saveAllTriggers(long version, Map<String, String> triggers) {
        this.redis.execute(jedis -> {
            String key = keyFor("triggers");
            jedis.hmset(key, triggers);
            jedis.hkeys(key).forEach(name -> {
                if (!triggers.containsKey(key)){
                    System.out.println("deleting unused task " + key + " in redis");
                    jedis.hdel(key, name);
                }
            });
            jedis.set(keyFor("version"), "" + version);
        });
    }

    @Override
    public boolean grabTask(String name) {
        Holder<Boolean> holder = new Holder<>();
        redis.execute(jedis -> {
            String lockKey = keyFor("task_lock", name);
            String ok = jedis.set(lockKey, "true", SetParams.setParams().nx().ex(lockAge));
            holder.value(ok != null);
        });
        return holder.value();
    }
}
