package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicLong;

// AtomicLong 保证了原子性，结果虽然对，但是打印顺序错误的
public class Demo29 {
    @SneakyThrows
    public static void main(String[] args) {
        Myservice myservice = new Myservice();
        MyThread[] myThreads = new MyThread[5];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] =new MyThread(myservice);
        }

        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i].start();
        }

        Thread.sleep(1000);
        System.out.println(myservice.aiRef.get());

    }

    static class Myservice{
        public static AtomicLong aiRef = new AtomicLong();
        // synchronized保证顺序
         public void addNum(){
            System.out.println(Thread.currentThread().getName() + " 加了100后的值是"+ aiRef.addAndGet(100));
            aiRef.addAndGet(1);
        }


    }

    static class MyThread extends Thread{
        private Myservice myservice;

        public MyThread(Myservice myservice) {
            this.myservice = myservice;
        }

        @Override
        public void run() {
            myservice.addNum();
        }
    }
}
