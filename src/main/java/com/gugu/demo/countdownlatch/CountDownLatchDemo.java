package com.gugu.demo.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @Classname CountDownLatchDemo
 * @Description CountDownLatch
 * @Date 2022/6/3 14:23
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.print(1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            }.start();
        }
        countDownLatch.await();
        System.out.println(countDownLatch.getCount());
        System.out.println(String.format("耗时：%sms", System.currentTimeMillis()-start));
    }
}
