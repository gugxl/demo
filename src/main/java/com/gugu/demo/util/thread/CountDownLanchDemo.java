package com.gugu.demo.util.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class CountDownLanchDemo {
    static final BlockingQueue<Integer> queue = new ArrayBlockingQueue(1);

    public static void main(String[] args) throws InterruptedException {

        int threads = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            SubThread subThread = new SubThread(countDownLatch, 20000 * (i + 1));
            subThread.start();
        }
        mainThreadOtherWork();
        System.out.println("now waiting sub thread done");
        countDownLatch.await();
        System.out.println("now all done.");

    }

    private static void mainThreadOtherWork() {
        System.out.println("main thread work start");
        try {
            Thread.sleep(3000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("main thread work done");
    }

    public static class SubThread extends Thread {
        private CountDownLatch countDownLatch;
        private long work;

        public SubThread(CountDownLatch countDownLatch, long work) {
            this.countDownLatch = countDownLatch;
            this.work = work;
        }

        @Override
        public void run() {
            try {
                working();
            } finally {
                countDownLatch.countDown();
            }
        }

        private void working() {
            System.out.println(getName() + "sub thread start working ");
            busy();
            System.out.println(getName() + "sub thread stop working ");
        }

        private void busy() {
            try {
                sleep(work);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
