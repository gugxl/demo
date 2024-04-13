package com.gugu.demo.book.book1.chapter1;

public class Demo07 {
    public static void main(String[] args) {

        Thread thread = new Thread() {
            // 匿名内部类的初始化方法,但是不是构造方法，匿名内部类没有构造方法
            {
                System.out.println("CountOperate  -- begin");
                System.out.println("Thread.currentThread().getName() :" + Thread.currentThread().getName());
                System.out.println("Thread.currentThread().isAlive() :" + Thread.currentThread().isAlive());
                System.out.println("this.getName():" + this.getName());
                System.out.println("this.getName():" + this.isAlive());
                System.out.println("CountOperate  -- end");
            }

            @Override
            public void run() {
                System.out.println("run  -- begin");
                System.out.println("Thread.currentThread().getName() :" + Thread.currentThread().getName());
                System.out.println("Thread.currentThread().isAlive() :" + Thread.currentThread().isAlive());
                System.out.println("this.getName():" + this.getName());
                System.out.println("this.getName():" + this.isAlive());
                System.out.println("run  -- end");

            }
        };

        System.out.println("main begin thread isAlive:" + thread.isAlive());
        thread.setName("A");
        thread.start();
        System.out.println("main end thread isAlive:" + thread.isAlive());

    }

}

