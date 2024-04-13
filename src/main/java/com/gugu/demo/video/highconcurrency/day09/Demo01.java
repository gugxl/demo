package com.gugu.demo.video.highconcurrency.day09;

import org.jetbrains.annotations.NotNull;

public class Demo01 {
    public static void main(String[] args) {
        T1 t1 = new T1("子线程1");
        t1.setDaemon(true); // 如果不设置为守护线程，会一直不结束
        t1.start();
        System.out.println("主线程结束");
    }

    static class T1 extends Thread {
        public T1(@NotNull String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "开始执行" + (this.isDaemon() ? "我是守护线程" : "我不是守护线程"));
            while (true) ;
        }
    }
}
