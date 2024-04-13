package com.gugu.demo.video.highconcurrency.day06;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

/**
 * 死锁
 */
public class Demo05 {
    static Object object = new Object();
    @SneakyThrows
    public static void main(String[] args) {
        T1 t1 = new T1("t1");
        t1.start();
        Thread.sleep(1000);
        T1 t2 = new T1("t2");
        t2.start();

        t1.resume();
        t2.resume();

        t1.join();
        t2.join();


    }

    static class T1 extends Thread{
        public T1(@NotNull String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("in " + this.getName());
                Thread.currentThread().suspend();
            }
        }
    }
}
