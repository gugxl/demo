package com.gugu.demo.util.thread.unsafe;

/**
 * @author gugu
 * @Classname CounterClient 工作线程
 * @Description TODO
 * @Date 2022/9/3 10:16
 */
public class CounterClient implements Runnable {
    private final Counter c;
    private final int num;

    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
        }
    }
}
