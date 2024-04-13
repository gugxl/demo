package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Demo08 {
    @SneakyThrows
    public static void main(String[] args) {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        new Thread(() -> {
            subtract.substract();
        }, "subtractA").start();
        new Thread(() -> {
            subtract.substract();
        }, "subtractB").start();
        Thread.sleep(1000);
        new Thread(() -> {
            add.add();
        }, "add").start();

    }

    static class Add {
        private String lock;

        public Add(String lock) {
            this.lock = lock;
        }

        public void add() {
            synchronized (lock) {
                ValueObject.list.add("gugu");
                lock.notifyAll();
            }

        }
    }

    static class Subtract {
        private String lock;

        public Subtract(String lock) {
            this.lock = lock;
        }

        public void substract() {
            synchronized (lock) {

                try {
                    //  如果是if 会 两个线程都执行第二个线程执行的时候下标越界，while定时判断即可
                    if (ValueObject.list.size() == 0) {
                        System.out.println("wait begin threadName = " + Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("wait end threadName = " + Thread.currentThread().getName());

                    }
                    ValueObject.list.remove(0);
                    System.out.println("list size =" + ValueObject.list.size());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    static class ValueObject {
        static List list = new ArrayList<String>();
    }
}
