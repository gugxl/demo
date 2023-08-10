package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        myThread2.setName("myThread2");
        myThread2.start();
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            Thread.sleep(time);
            System.out.println("run ==="+Thread.currentThread().getName());
        }


        new Thread(new MyRunnable()).start();

    }
}

class MyThread2 extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random() * 1000);
            Thread.sleep(time);
            System.out.println("run ==="+Thread.currentThread().getName());

        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("MyRunnable");
    }
}