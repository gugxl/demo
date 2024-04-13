package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
// Thread.sleep(100) 的时候 改变了锁的对象，导致 Thread B获取的锁对象是 "456" 两个是异步的
// 去掉Thread.sleep(100) ,两个线程同时抢占 "123" 锁对象，两个线程是同步的
public class Demo20 {
    @SneakyThrows
    public static void main(String[] args) {
        MyService myService = new MyService();
        new Thread1("A",myService).start();
        Thread.sleep(100);
        new Thread1("B",myService).start();

    }

    static class MyService{
        private String lock = "123";
        @SneakyThrows
        public void method(){
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());

                lock = "456";
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());

            }
        }
    }

    static class Thread1 extends Thread{
        private MyService service;

        public Thread1(@NotNull String name, MyService service) {
            super(name);
            this.service = service;
        }

        @Override
        public void run() {
            service.method();
        }
    }

}
