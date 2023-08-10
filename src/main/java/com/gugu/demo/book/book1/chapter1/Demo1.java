package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Mythread mythread = new Mythread();
        mythread.start();

        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
    }

}
class Mythread extends Thread{
    @Override
    public void run() {
        System.out.println("Mythread");
    }
}

class MyThread1 extends Thread{
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