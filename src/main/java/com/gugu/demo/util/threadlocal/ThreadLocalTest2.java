package com.gugu.demo.util.threadlocal;

/**
 * @author gugu
 * @Classname ThreadLocalTest2
 * @Description TODO
 * @Date 2022/9/6 22:08
 */
public class ThreadLocalTest2 {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                threadLocal.remove();
            }

        }, "threadLocal1").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                threadLocal.remove();
            }

        }, "threadLocal2").start();
    }

}
