package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo12 {
    public static void main(String[] args) {
        Service12 service12 = new Service12();
        new Service12.ThreadA12("A",service12).start();
        new Service12.ThreadB12("B",service12).start();
        new Service12.ThreadC12("C",service12).start();
    }

    static class Service12 {
        @SneakyThrows
        synchronized public static void printA() {
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(1000);
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printA");
        }

        @SneakyThrows
        synchronized public static void printB() {
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printB");
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printB");
        }

        @SneakyThrows
        synchronized public void printC() {
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printC");
            System.out.println("threadName=" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printC");
        }

        static class ThreadA12 extends Thread {
            private Service12 service12;

            public ThreadA12(@NotNull String name, Service12 service12) {
                super(name);
                this.service12 = service12;
            }

            @Override
            public void run() {
                service12.printA();
            }
        }

        static class ThreadB12 extends Thread {
            private Service12 service12;

            public ThreadB12(@NotNull String name, Service12 service12) {
                super(name);
                this.service12 = service12;
            }

            @Override
            public void run() {
                service12.printB();
            }
        }

        static class ThreadC12 extends Thread {
            private Service12 service12;

            public ThreadC12(@NotNull String name, Service12 service12) {
                super(name);
                this.service12 = service12;
            }

            @Override
            public void run() {
                service12.printC();
            }
        }
    }
}


