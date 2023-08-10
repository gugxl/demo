package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo06 {
    public static void main(String[] args) {

        MyThread06 myThread06 = new MyThread06();

            Thread t1 = new Thread(myThread06);
            Thread t2 = new Thread(myThread06);
            Thread t3 = new Thread(myThread06);
            Thread t4 = new Thread(myThread06);
            Thread t5 = new Thread(myThread06);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();





    }
}
class MyThread06 extends Thread{
    private int i = 5;
    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(1000);
        // i-- 不是原子操作会会有线程安全问题，println方法是同步的
        System.out.println("i=" + (i--) + ",threadName:" + Thread.currentThread().getName());
    }
}