package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo12 {
    @SneakyThrows
    public static void main(String[] args) {
        final SynchronizedObject synchronizedObject = new SynchronizedObject();
        new Thread(() -> {
            synchronizedObject.printString();
        }, "a").start();

        Thread.sleep(1000);
        new Thread(() -> {
            System.out.println("thread2 启动了，但是进入不了printString方法， 因为printString 被a线程永远的暂停了");
            synchronizedObject.printString();
        }).start();
    }

    static class SynchronizedObject {
        synchronized void printString(){
            System.out.println("begin");
            if (Thread.currentThread().getName().equals("a")){
                System.out.println("a 线程永远suspend了");
                Thread.currentThread().suspend();
            }
            System.out.println("end");
        }
    }

}

