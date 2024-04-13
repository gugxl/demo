package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

public class Demo03 {
    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadA(lock).start();
        Thread.sleep(1000);
        new ThreadB(lock).start();

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
                System.out.println("开始 wait time " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束 wait time " + System.currentTimeMillis());
            }
        }
    }

    static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("开始 notify time " + System.currentTimeMillis());
                lock.notify();
                System.out.println("结束 notify time " + System.currentTimeMillis());
            }
        }
    }

}
