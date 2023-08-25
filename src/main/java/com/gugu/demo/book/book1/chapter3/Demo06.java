package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

// wait 释放锁 notify 不会释放锁必须执行完notify所在的synchronize代码块才会释放锁， sleep 也不会释放锁，但是达到了同步的效果
public class Demo06 {
    public static void main(String[] args) {
        Object lock = new Object();
        new ThreadA(lock).start();
        new NotifyThread(lock).start();
        new synNotifyThread(lock).start();

    }

    static class Service {
        @SneakyThrows
        void testMethod(Object lock) {
            System.out.println("begin wait " + Thread.currentThread().getName());
            lock.wait();
            System.out.println("end wait " + Thread.currentThread().getName());

        }

        @SneakyThrows
        void synNotifyMethod(Object lock) {
            synchronized (lock) {
                System.out.println("begin notify " + Thread.currentThread().getName());
                lock.notify();
                Thread.sleep(1000);
                System.out.println("end notify " + Thread.currentThread().getName());
            }
        }
    }

    static class ThreadA extends Thread {
        private Object lock;

        public ThreadA(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            new Service().synNotifyMethod(lock);
        }
    }

    static class NotifyThread extends Thread {
        private Object lock;

        public NotifyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            new Service().synNotifyMethod(lock);
        }
    }

    static class synNotifyThread extends Thread {
        private Object lock;

        public synNotifyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            new Service().synNotifyMethod(lock);
        }
    }

}
