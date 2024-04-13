package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo10 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] pThreads = new ThreadP[2];
        ThreadC[] cThreads = new ThreadC[2];
        for (int i = 0; i < pThreads.length; i++) {
            cThreads[i] = new ThreadC("消费者" + i, c);
            pThreads[i] = new ThreadP("生产者" + i, p);
            pThreads[i].start();
            cThreads[i].start();
        }
        Thread.sleep(100000);
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].getName() + " " + threads[i].getState());

        }
    }

    static class P {
        private String lock;

        public P(String lock) {
            this.lock = lock;
        }

        @SneakyThrows
        public void setValue() {
            synchronized (lock) {
                while (!ValueObject.value.equals("")) {
                    System.out.println(Thread.currentThread().getName() + " WATING了");
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " RUNNABLE了");
                ValueObject.value = System.currentTimeMillis() + "_" + System.nanoTime();
                lock.notify();
            }
        }
    }

    static class C {
        private String lock;

        public C(String lock) {
            this.lock = lock;
        }

        @SneakyThrows
        public void getValue() {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println(Thread.currentThread().getName() + " WAITING了");
                    lock.wait();

                    System.out.println(Thread.currentThread().getName() + " RUNNABLE了");
                    ValueObject.value = "";
                    lock.notify();
                }
            }
        }
    }

    static class ThreadP extends Thread {
        private P p;

        public ThreadP(@NotNull String name, P p) {
            super(name);
            this.p = p;
        }

        @Override
        public void run() {
            while (true)
                p.setValue();
        }
    }

    static class ThreadC extends Thread {
        private C c;

        public ThreadC(@NotNull String name, C c) {
            super(name);
            this.c = c;
        }

        @Override
        public void run() {
            while (true)
                c.getValue();
        }
    }

    static class ValueObject {
        static String value = "";
    }
}
