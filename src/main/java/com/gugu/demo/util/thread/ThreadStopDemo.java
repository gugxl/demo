package com.gugu.demo.util.thread;

import lombok.SneakyThrows;

/**
 * @author Administrator
 * @Classname ThreadStopDemo
 * @Description Thread.stop() 方法是不安全的，一般替换方法是使用信号量来控制，需要注意的是信号量要volatile，否则会一直等待线程将缓存刷新到主线程才会生效
 * @Date 2022/6/3 15:16
 */
public class ThreadStopDemo extends Thread {
    private static volatile boolean stop = false;

    @SneakyThrows
    public static void main(String[] args) {
        ThreadStopDemo threadStopDemo = new ThreadStopDemo();
        threadStopDemo.start();
        Thread.sleep(1000);
        stop = true;
    }

    @Override
    public void run() {
        while (!stop){

        }
    }
}
