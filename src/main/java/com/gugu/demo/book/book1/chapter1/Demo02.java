package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

/**
 * @author Administrator
 */
public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int time = (int) (Math.random() * 1000);
                    Thread.sleep(time);
                    System.out.println("run ===" + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "myThread2").start();


        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            Thread.sleep(time);
            System.out.println("run ===" + Thread.currentThread().getName());
        }

        new Thread(() -> System.out.println("MyRunnable")).start();

    }
}

