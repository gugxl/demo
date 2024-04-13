package com.gugu.demo.util.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Administrator
 * @Classname CyclicBarrierDemo
 * @Description TODO
 * @Date 2022/6/3 14:43
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // parties 需要几个线程到达进行回调，要与下面线程的数量倍数关系，否则会有线程一直等待
        CyclicBarrier cb = new CyclicBarrier(5, () -> {
            System.out.println("cb finish");
        });

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        cb.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s 耗时：%sms", Thread.currentThread().getName(), System.currentTimeMillis() - start));
                }
            }.start();
        }


    }
}
