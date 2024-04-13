package com.gugu.demo.util.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @Classname ThreadTest
 * @Description TODO
 * @Date 2021/10/16 23:22
 */
public class ThreadTest {
    private int j;
    private Lock lock =new ReentrantLock();

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();

        for (int i = 0; i < 2; i++) {
            new Thread(threadTest.new Adder()).start();
            new Thread(threadTest.new Subtractor()).start();
        }
    }

    class Subtractor implements Runnable{
        @Override
        public void run() {
            while (true){
                lock.lock();

                try {
                    System.out.println("j--="+j--);
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Adder implements Runnable{
        @Override
        public void run() {
            while (true){
                lock.lock();

                try {
                    System.out.println("j++="+j++);
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}

