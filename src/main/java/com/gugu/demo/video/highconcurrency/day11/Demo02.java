package com.gugu.demo.video.highconcurrency.day11;

public class Demo02 {
    
    private static int num = 0;

    private static synchronized void add(){
        num ++;
    }

    public static class T extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Demo02.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Demo02.num);
    }

}
