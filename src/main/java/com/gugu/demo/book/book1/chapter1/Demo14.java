package com.gugu.demo.book.book1.chapter1;

public class Demo14 {
    public static void main(String[] args) {
         new Thread(){
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                int count  = 0;
                for (int i = 0; i < 500000; i++) {
//                    Thread.yield();
                    count+= i;

                }
                long endTime = System.currentTimeMillis();
                System.out.println("耗时："+(endTime-startTime));

            }
        }.start();
    }
}
