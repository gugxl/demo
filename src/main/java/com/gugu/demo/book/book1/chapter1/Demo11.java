package com.gugu.demo.book.book1.chapter1;

import lombok.Data;
import lombok.SneakyThrows;

public class Demo11 {
    @SneakyThrows
    public static void main(String[] args) {
        MyThread11 myThread11 = new MyThread11();

        myThread11.start();
        Thread.sleep(5000);
        myThread11.suspend();
        System.out.println("A="+System.currentTimeMillis() + "   i" + myThread11.getI());
        Thread.sleep(5000);
        System.out.println("A="+System.currentTimeMillis() + "   i" + myThread11.getI());

        // B段
        myThread11.resume();
        Thread.sleep(5000);

        // C段
        myThread11.suspend();
        System.out.println("B="+System.currentTimeMillis() + "   i" + myThread11.getI());
        Thread.sleep(5000);
        System.out.println("B="+System.currentTimeMillis() + "   i" + myThread11.getI());
    }

    @Data
    static class MyThread11 extends Thread{
        private long i = 0;

        @Override
        public void run() {
            while (true){
                i++;
            }

        }
    }
}

