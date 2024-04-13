package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo08 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread myThread08 = new Thread() {
            @Override
            public void run() {
                // 由于Runable是接口没有this对象不能改造为lambda方式
                System.out.println("run isAlive:" + this.isAlive());
            }
        };
        System.out.println("before myThread08 isAlive:" + myThread08.isAlive());
        myThread08.start();
        Thread.sleep(1000);
        System.out.println("after myThread08 isAlive:" + myThread08.isAlive());

    }

}

