package com.gugu.demo.util.thread;

/**
 * @author Administrator
 * @Classname ARunnableaDemo
 * @Description TODO
 * @Date 2021/5/3 10:42
 */
public class ARunnableaDemo {
    public static void main(String[] args) {
        new Thread(new ARunnanle()).start();
    }
}
class ARunnanle implements Runnable{
    @Override
    public void run() {
        System.out.println("Current Thread Name:" + Thread.currentThread().getName());
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}