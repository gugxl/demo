package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;
// 执行完同步代码块就会释放锁
//  同步代码块中有异常导致线程终止也会释放锁
public class Demo07 {
    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            new Service().testMode(lock);
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    static class Service {
        public void testMode(Object lock) {
            synchronized (lock) {
                try {
                    System.out.println("begin wait");
                    lock.wait();
                    System.out.println("end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("异常了wait被Interrupted了");
                }
            }
        }
    }
}
