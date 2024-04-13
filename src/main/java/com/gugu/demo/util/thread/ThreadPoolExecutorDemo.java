package com.gugu.demo.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Classname ThreadPoolExecutorDemo
 * @Description TODO
 * @Date 2022/6/2 23:06
 */
public class ThreadPoolExecutorDemo {
           private static ExecutorService es = new ThreadPoolExecutor(50, 100, 0L,TimeUnit.MICROSECONDS,
                   new LinkedBlockingQueue<Runnable>(100000));
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            es.execute(() -> {
                System.out.print(1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) es;
        while (true && tpe.getQueue().size() > 0){

            System.out.println();
            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数量：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动队线程数量：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("当前执行完成线程数量：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数："+ taskCount);

            Thread.sleep(1000);
        }

    }
}
