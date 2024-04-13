package com.gugu.demo.video.highconcurrency.day06;

/**
 * Object.wait()方法和Thread.sleeep()方法都可以让现场等待若干时间。除wait()方法可以被唤醒外，另外一个主要的区别就是wait()方法会释放目标对象的锁，而Thread.sleep()方法不会释放锁。
 */
public class Demo04 {
    static Object object = new Object();

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start，notify one thread!");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
