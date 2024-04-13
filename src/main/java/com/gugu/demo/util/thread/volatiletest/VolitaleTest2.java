package com.gugu.demo.util.thread.volatiletest;

/**
 * @author Administrator
 * @Classname VolitaleTest2
 * @Description TODO
 * @Date 2022/6/3 8:37
 */
public class VolitaleTest2 {
    private static volatile int n = 0;
    public synchronized static void add(){
        n++;
    }

    public static void main(String[] args) {
        new Thread1().start();
        while (n < 100){

        }
        System.out.println("stop");
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 200; i++) {
                add();
            }
        }
    }
}
