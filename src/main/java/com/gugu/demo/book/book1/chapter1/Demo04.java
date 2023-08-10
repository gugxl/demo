package com.gugu.demo.book.book1.chapter1;

public class Demo04 {
    public static void main(String[] args) {
        MyThread04 myThread04 = new MyThread04();
        Thread a = new Thread(myThread04, "A");
        Thread b = new Thread(myThread04, "B");
        Thread c = new Thread(myThread04, "C");
        Thread d = new Thread(myThread04, "D");

        a.start();
        b.start();
        c.start();
        d.start();

    }
}
class MyThread04 extends Thread{
    private int count = 5;
    // synchronized可以保证线程安全，顺序执行
    @Override
     public void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"执行count:"+count);
    }
}
