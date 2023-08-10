package com.gugu.demo.book.book1.chapter1;

import org.jetbrains.annotations.NotNull;

public class Demo03 {
    public static void main(String[] args) {
        MyThread3 a = new MyThread3("A");
        MyThread3 b = new MyThread3("B");
        MyThread3 c = new MyThread3("C");
        a.start();
        b.start();
        c.start();
    }

}

class MyThread3 extends Thread{
    private int count = 5;

    public MyThread3(@NotNull String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (count > 0 ){
            count--;
            System.out.println(Thread.currentThread().getName() + "执行count:" + count);
        }
    }
}
