package com.gugu.demo.book.book1.chapter2;

// volatile 保证可见性，但是不保证原子性
public class Demo25 {
    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            myThreads[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            myThreads[i].start();
        }
    }


    static class MyThread extends Thread {
        volatile public static int count ;
        //synchronized + static 锁住这个类保证同步效果
        // 也可以把++ 换成原子操作AtomicInteger
         private static void addCount(){
            for (int i = 0; i < 100; i++) {
                count++;
            }
            System.out.println("count="+count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
