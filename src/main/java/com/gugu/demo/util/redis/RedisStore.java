package com.gugu.demo.util.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * @author Administrator
 * @Classname RedisStore
 * @Description redis连接池
 * @Date 2021/11/21 12:35
 */
public class RedisStore {
    private List<JedisPool> jedisPools;

    public RedisStore() {
        this(URI.create("redis://192.168.2.200:6379/0"));
    }

    public RedisStore(URI... uris) {
        jedisPools = new ArrayList<>(uris.length);
        // 初始化链接
        for (URI uri : uris){
            JedisPool pool = new JedisPool(uri, 500);
            jedisPools.add(pool);
        }
    }

    public void close(){
        for (JedisPool jedisPool : jedisPools) {
            jedisPool.close();
        }
    }

    public void execute(Consumer<Jedis> func){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt(jedisPools.size());

        // 随机取出一个
        JedisPool pool = jedisPools.get(i);
        try (Jedis jedis = pool.getResource()){
            try {
                func.accept(jedis);
            } catch(JedisConnectionException e){
                    func.accept(jedis);
                }
            }
    }
}
