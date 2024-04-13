package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo14 {
    public static void main(String[] args) {
        Service service = new Service();
        new ThreadA("A", service).start();
        new ThreadB("B", service).start();
    }

    static class Service {
        @SneakyThrows
        public static void print(Object object) {
            synchronized (object) {
                while (true){
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
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
            service.print(new Object());
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
            service.print(new Object());
        }
    }
}
