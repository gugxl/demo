package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo18 {
    public static void main(String[] args) {
        final OutClass.Inner inner = new OutClass.Inner();
        new Thread(()->{
            inner.method1();
        },"A").start();
        new Thread(()->{
            inner.method2();
        },"B").start();
    }

    static class OutClass {
        static class Inner{
            @SneakyThrows
            public void method1(){
                synchronized ("其他锁") {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + ",i=" + i);
                        Thread.sleep(100);
                    }
                }
            }

            @SneakyThrows
            synchronized public void method2(){
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + ",i=" + i);
                    Thread.sleep(100);
                }
            }
        }
    }
}


