package com.gugu.demo.video.count;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MapExample {

    private static Map<Integer, Integer> map = Maps.newHashMap();
    private static int threadTotal = 100;
    private static int clientTotal = 5000;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int threadName = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadName);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });

        }

        executorService.shutdown();
        System.out.println(map.size());
    }

    public static void func(int threadName) {
        map.put(threadName, threadName);
    }
}
