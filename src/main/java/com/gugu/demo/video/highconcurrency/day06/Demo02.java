package com.gugu.demo.video.highconcurrency.day06;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("退出");
                break;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

    }

}
