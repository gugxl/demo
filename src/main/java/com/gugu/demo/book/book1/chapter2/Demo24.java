package com.gugu.demo.book.book1.chapter2;

public class Demo24 {
    static class RunThread extends Thread {
        volatile public boolean isRunning = true;

        @Override
        public void run() {
            System.out.println("进入run ");
            while (isRunning) {
            }
            System.out.println("结束");
        }

        public static void main(String[] args) {
            RunThread runThread = new RunThread();
            runThread.start();
            runThread.isRunning = (false);

        }

    }
}