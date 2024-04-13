package com.gugu.demo.book.book1.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo26 {

    public static void main(String[] args) {
        AddCountThread addCountThread = new AddCountThread();
        new Thread(addCountThread).start();
        new Thread(addCountThread).start();
        new Thread(addCountThread).start();
        new Thread(addCountThread).start();
        new Thread(addCountThread).start();
    }
    static class AddCountThread extends Thread{
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println(count.incrementAndGet());
            }
        }
    }
}
