package com.gugu.demo.video.highconcurrency.day06;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo06 {
    static int num = 0;

    @SneakyThrows
    public static void main(String[] args) {
        T1 t1 = new T1("t1");
        t1.start();
        t1.join();
        System.out.println(System.currentTimeMillis() + ",num " + num);
    }

    static class T1 extends Thread {
        public T1(@NotNull String name) {
            super(name);
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ",start " + this.getName());
            for (int i = 0; i < 10; i++) {
                num++;
                Thread.sleep(100);
            }
            System.out.println(System.currentTimeMillis() + ",end " + this.getName());

        }
    }
}
