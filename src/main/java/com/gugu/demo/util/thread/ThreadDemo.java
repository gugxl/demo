package com.gugu.demo.util.thread;

/**
 * @author Administrator
 * @Classname ThreadDemo
 * @Description TODO
 * @Date 2021/5/1 13:20
 */
public class ThreadDemo {
    public static void main(String[] args) {
        AThread aThread = new AThread();
        aThread.start();
    }

}
class AThread extends Thread{
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