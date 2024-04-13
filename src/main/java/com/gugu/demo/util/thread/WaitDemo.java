package com.gugu.demo.util.thread;

/**
 * @author gugu
 * @Classname WaitDemo
 * @Description wait notify notifyAll 使用
 * @Date 2022/6/3 21:30
 */
public class WaitDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    System.out.print(i+" ");
                    if (i==10){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("gugu");
                lock.notifyAll();
            }
        });

        thread1.start();
        thread2.start();
    }
}
