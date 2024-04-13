package com.gugu.demo.video.highconcurrency.day11;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.locks.ReentrantLock;
// 公平锁
public class Demo04 {
    private static int num = 0;
    private static ReentrantLock fairLock = new ReentrantLock(true);

    public static class T extends Thread{
        public T(@NotNull String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                fairLock.lock();
                try {
                    System.out.println(this.getName() + "获得锁!");
                } finally {
                    fairLock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

}
