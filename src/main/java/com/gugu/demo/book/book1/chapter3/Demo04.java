package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Demo04 {
    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadA(lock).start();
        Thread.sleep(100);
        new ThreadB(lock).start();
    }

    static class MyList {
        static List list = new ArrayList<String>();

        static void add() {
            list.add("gugu");
        }

        static int size() {
            return list.size();
        }
    }

    static class ThreadA extends Thread {
        private Object lock;

        public ThreadA(Object lock) {
            this.lock = lock;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                if (MyList.size() != 5) {
                    System.out.println(" wait begin time" + System.currentTimeMillis());
                    lock.wait();
                    System.out.println(" wait end time" + System.currentTimeMillis());
                }
            }
        }
    }

    static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(Object lock) {
            this.lock = lock;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notify();
                        System.out.println("已经发出通知");

                    }
                    System.out.println("添加了" + MyList.size() + "元素");
                    Thread.sleep(1000);
                }
            }
        }
    }
}
