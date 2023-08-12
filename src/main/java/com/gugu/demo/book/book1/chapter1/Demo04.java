package com.gugu.demo.book.book1.chapter1;

public class Demo04 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int count = 5;
            // synchronized可以保证线程安全，顺序执行
            @Override
            synchronized public void run() {
                count--;
                System.out.println(Thread.currentThread().getName()+"执行count:"+count);
            }
        };

        Thread a = new Thread(runnable, "A");
        Thread b = new Thread(runnable, "B");
        Thread c = new Thread(runnable, "C");
        Thread d = new Thread(runnable, "D");

        a.start();
        b.start();
        c.start();
        d.start();

    }
}
