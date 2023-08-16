package com.gugu.demo.book.book1.chapter2;

import org.jetbrains.annotations.NotNull;

// 死锁, 如果锁的是不同资源就可以打破死锁 比如下面object和object2
public class Demo15 {
    public static void main(String[] args) {
        Service service = new Service();
        new ThreadA("A", service).start();
        new ThreadB("B", service).start();
    }

    static class Service {
        Object object = new Object();

        public void methodA() {
            synchronized (object) {
                System.out.println("method A begin");
                boolean isContinurun = true;
                while (isContinurun) {

                }
                System.out.println("method A end");
            }

        }
        Object object2 = new Object();

        public void methodB() {
            synchronized (object) {
                System.out.println("method B start");
                System.out.println("method B end");
            }

        }

    }

    static class ThreadA extends Thread {
        private Service service;

        public ThreadA(@NotNull String name, Service service) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            service.methodA();
        }
    }

    static class ThreadB extends Thread {
        private Service service;

        public ThreadB(@NotNull String name, Service service) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            service.methodB();
        }
    }
}
