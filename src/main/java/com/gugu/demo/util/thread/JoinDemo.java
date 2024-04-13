package com.gugu.demo.util.thread;

import lombok.SneakyThrows;

/**
 * @author Administrator
 * @Classname JoinDemo
 * @Description join thread执行完之后再执行主线程
 * @Date 2022/6/3 15:47
 */
public class JoinDemo {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("thread start...");
        Thread thread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                }
            }
        };
        thread.start();
        thread.join();
        System.out.println("thread end");
    }
}
