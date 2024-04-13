package com.gugu.demo.video.highconcurrency.day09;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

// 守护线程的默认属性与创建这个线程的线程一致
public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ".daemon: " + Thread.currentThread().isDaemon());
        T1 t1 = new T1("t1");
        t1.start();

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                System.out.println(this.getName() + ".daemon: " + this.isDaemon());
                T1 t3 = new T1("t3");
                t3.start();
            }
        };
        t2.setDaemon(true);
        t2.start();

        TimeUnit.SECONDS.sleep(1);
    }

    static class T1 extends Thread {
        public T1(@NotNull String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + ".daemon: " + this.isDaemon());
        }
    }


}
