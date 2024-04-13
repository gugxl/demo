package com.gugu.demo.util.redis;

import java.util.Date;

/**
 * @author Administrator
 * @Classname Demo
 * @Description TODO
 * @Date 2021/11/21 12:22
 */
public class RedisTaskDemo {
    public static void main(String[] args) {
        RedisStore redisStore = new RedisStore();
        // sample 为任务分组名称
        RedisTaskStore store = new RedisTaskStore(redisStore, "sample");
        DistributedScheduler scheduler = new DistributedScheduler(store, 5);
        scheduler.register(Trigger.once(new Date(0)), Task.of("once1", () -> {
            System.out.println("once1");
        }));
        scheduler.register(Trigger.period(new Date(0), 5), Task.of("period2", () -> {
            System.out.println("period2");
        }));

        scheduler.register(Trigger.cronOfMinutes(2), Task.of("cron3", () -> {
            System.out.println("cron3");
        }));

        scheduler.register(Trigger.period(new Date(0), 10), Task.of("period4", () -> {
            System.out.println("period4");
        }));

        scheduler.version(4);
        scheduler.listener(ctx -> {
            System.out.println(ctx.task().getName() + " is complete");
        });

        scheduler.start();
    }
}
