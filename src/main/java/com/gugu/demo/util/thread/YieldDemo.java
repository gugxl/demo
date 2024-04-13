package com.gugu.demo.util.thread;

/**
 * @author gugu
 * @Classname YieldDemo
 * @Description Thread.yield()当前线程让出cpu，重新竞争
 * @Date 2022/6/3 21:17
 */
public class YieldDemo {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i);
                if (i%20 == 0){
                    Thread.yield();
                }
            }
        };

        Thread thread1 = new Thread(runnable, "小谷");
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new Thread(runnable, "小可爱");
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
