package com.gugu.demo.util.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @Classname ExecutorsDemo
 * @Description TODO
 * @Date 2021/10/16 20:24
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("FixedThreadPool:"+Thread.currentThread().getName());
                }
            });
        }

        ExecutorService pool1 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            pool1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("CachedThreadPool:"+Thread.currentThread().getName());
                }
            });
        }


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("SingleThreadExecutor:"+Thread.currentThread().getName());
                }
            });
        }
    }
}
