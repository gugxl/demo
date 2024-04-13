package com.gugu.demo.book.book1.chapter1;

public class Demo03 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int count = 5;

            @Override
            public void run() {
                while (count > 0) {
                    count--;
                    System.out.println(Thread.currentThread().getName() + "执行count:" + count);
                }
            }
        };

        Thread a = new Thread(runnable, "A");
        Thread b = new Thread(runnable, "B");
        Thread c = new Thread(runnable, "C");

        a.start();
        b.start();
        c.start();
    }

}


