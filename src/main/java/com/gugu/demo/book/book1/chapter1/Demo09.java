package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo09 {
    @SneakyThrows
    public static void main(String[] args) {
//        MyThread9 myThread9 = new MyThread9();
//        myThread9.start();
//        Thread.sleep(2000);

        Thread.currentThread().interrupt();
//Thread.interrupted() 会清除中断状态
//        isInterrupted不是静态方法，也不会清除中断状态
        System.out.println(Thread.interrupted());

        System.out.println(Thread.interrupted());
    }
}

class MyThread9 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5000000; i++) {
            System.out.println("i = "+ i);
            
        }
    }
}