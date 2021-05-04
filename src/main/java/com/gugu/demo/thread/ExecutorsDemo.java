package com.gugu.demo.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Classname ExecutorsDemo
 * @Description TODO
 * @Date 2021/5/3 22:01
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        AThreadFactory aThreadFactory = new AThreadFactory();
        ARunnanle aRunnanle = new ARunnanle();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(cpuCount, aThreadFactory);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(aThreadFactory);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(cpuCount, aThreadFactory);
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(aThreadFactory);

        fixedThreadPool.submit(aRunnanle);
        cachedThreadPool.submit(aRunnanle);
        scheduledThreadPool.scheduleAtFixedRate(aRunnanle, 0, 1, TimeUnit.SECONDS);
        singleThreadScheduledExecutor.scheduleWithFixedDelay(aRunnanle, 0, 100, TimeUnit.MILLISECONDS);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fixedThreadPool.shutdown();
        cachedThreadPool.shutdown();
        scheduledThreadPool.shutdownNow();
        singleThreadScheduledExecutor.shutdownNow();
    }
}

class ARunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Current Thread Name:" + Thread.currentThread().getName());
    }
}

class AThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread("aThread-" + threadNumber.incrementAndGet());
    }
}
