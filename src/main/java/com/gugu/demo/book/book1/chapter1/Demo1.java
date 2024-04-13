package com.gugu.demo.book.book1.chapter1;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        new Thread(() -> {
            System.out.println("MyThread");
        }).start();

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
        }).start();
    }
}
