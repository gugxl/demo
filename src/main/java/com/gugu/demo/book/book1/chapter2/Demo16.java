package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

// 循环等待对方资源，不可抢占 死锁
// 检查先jps查找线程id，然后jstack pid 查看状态  "Found 1 deadlock" 即为发现一个死锁
public class Demo16 {
    @SneakyThrows
    public static void main(String[] args) {
        DealThread dealThread = new DealThread();
        dealThread.setFlag("a");
        new Thread(dealThread).start();
        Thread.sleep(100);
        dealThread.setFlag("b");
        new Thread(dealThread).start();
    }

    static class DealThread implements Runnable {
        String username;
        Object lock1 = new Object();
        Object lock2 = new Object();

        public void setFlag(String username) {
            this.username = username;
        }

        @SneakyThrows
        @Override
        public void run() {

            if (username.equals("a")) {
                synchronized (lock1) {
                    System.out.println("username=" + username);
                    Thread.sleep(1000);
                    synchronized (lock2) {
                        System.out.println("按照lock1-> lock2 的顺序执行了");
                    }
                }
            }
            if (username.equals("b")) {
                synchronized (lock2) {
                    System.out.println("username=" + username);
                    Thread.sleep(1000);
                    synchronized (lock1) {
                        System.out.println("按照lock2-> lock1 的顺序执行了");
                    }
                }
            }


        }
    }
}
