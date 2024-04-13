package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

// 大部分情况下synchronize不会以string为锁对象，String线程池会导致只有一个锁对象
public class Demo13 {

    public static void main(String[] args) {
        Service service = new Service();
        new ThreadA("A",service).start();
        new ThreadB("B",service).start();

    }
    static class Service{
        @SneakyThrows
        public static void print(String param){
            synchronized (param) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        }
    }

    static class ThreadA extends Thread{
        private Service service;

        public ThreadA(@NotNull String name, Service service) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            service.print("AA");
        }
    }

    static class ThreadB extends Thread{
        private Service service;

        public ThreadB(@NotNull String name, Service service) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            service.print("AA");
        }
    }
}
