package com.gugu.demo.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * https://mp.weixin.qq.com/s/JVTtowoqsIixiaK8WL7wgQ
 */
public class PipelineTest {
    public static void main(String[] args) {
        noPipelined();
        pipelined();
    }

    private static void noPipelined() {
        Jedis jedis = new Jedis("192.168.2.200");
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            jedis.set("key" + i, "val" + i);
            jedis.del("key"+i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时：" + (endTime - beginTime) + "毫秒");
    }

    private static void pipelined() {
        Jedis jedis = new Jedis("192.168.2.200");
        long beginTime = System.currentTimeMillis();
        // 获取 Pipeline 对象
        Pipeline pipe = jedis.pipelined();
        // 设置多个 Redis 命令
        for (int i = 0; i < 100; i++) {
            pipe.set("key" + i, "val" + i);
            pipe.del("key"+i);
        }
        // 执行命令
        pipe.sync();
        long endTime = System.currentTimeMillis();
        System.out.println("执行耗时：" + (endTime - beginTime) + "毫秒");
    }


}
