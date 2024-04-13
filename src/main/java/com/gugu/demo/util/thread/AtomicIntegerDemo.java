package com.gugu.demo.util.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gugu
 * @Classname AtomicIntegerDemo
 * @Description TODO
 * @Date 2022/6/3 20:43
 */
public class AtomicIntegerDemo {

    static int count = 0; // volatile 只能解决可见性问题，不能解决原子性安全问题, 方式2 AtomicInteger使用 AtomicInteger count = new AtomicInteger(0);
    static CountDownLatch countDownLatch = new CountDownLatch(1000);

    @SneakyThrows
    public static void main(String[] args) {
        CountRunnable countRunnable = new CountRunnable();
        for (int i = 0; i < 1000; i++) {
            new Thread(countRunnable).start();
        }
        countDownLatch.await();
        System.out.println(count);
    }

    static class CountRunnable implements Runnable{

        private void count(){ // 方法1. 加synchronized 可以保证线程安全
            for (int i = 0; i < 1000; i++) {
                count++;
            }
        }

        @Override
        public void run() {
            count();
            countDownLatch.countDown();
        }
    }
}
