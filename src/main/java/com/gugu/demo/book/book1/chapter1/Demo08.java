package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo08 {
    @SneakyThrows
    public static void main(String[] args) {
        MyThread08 myThread08 = new MyThread08();
        System.out.println("before myThread08 isAlive:" + myThread08.isAlive());
        myThread08.start();
        Thread.sleep(1000);
        System.out.println("after myThread08 isAlive:" + myThread08.isAlive());

    }

}
class MyThread08 extends Thread{
    @Override
    public void run() {
        System.out.println("run isAlive:"  + this.isAlive());
    }
}
