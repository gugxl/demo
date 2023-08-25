package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

public class Demo05 {
    public static void main(String[] args) {
        Object lock = new Object();
        new MyThread(lock).start();
        new MyThread(lock).start();
    }

    static class Service {

        @SneakyThrows
        void testMethod(Object lock) {
            synchronized (lock) {
                System.out.println("begin wait ");
                lock.wait();
//                Thread.sleep(100); 换成这个是同步的效果，sleep不会释放锁
                System.out.println("end wait ");
            }
        }
    }

    static class MyThread extends Thread{
        private Object lock;

        public MyThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            new Service().testMethod(lock);
        }
    }

}
