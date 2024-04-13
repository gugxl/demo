package com.gugu.demo.video.highconcurrency.day08;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Demo01 {
    @SneakyThrows
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup, new R1(), "t1");
        Thread t2 = new Thread(threadGroup, new R1(), "t2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动线程数:" + threadGroup.activeCount());
        System.out.println("活动线程组:" + threadGroup.activeGroupCount());
        System.out.println("线程组名称:" + threadGroup.getName());

    }

    static class R1 implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("threadName:" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
