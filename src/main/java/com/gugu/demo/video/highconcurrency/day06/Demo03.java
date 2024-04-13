package com.gugu.demo.video.highconcurrency.day06;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
 */
public class Demo03 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
//                        this.interrupt();
//                        sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
                        e.printStackTrace();
//                        throw new RuntimeException(e);
                    }
                    if (this.isInterrupted()){
                        System.out.println("退出");
                        break;
                    }
                }
            }
        };
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

    }
}
