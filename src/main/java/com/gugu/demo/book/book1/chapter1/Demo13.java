package com.gugu.demo.book.book1.chapter1;

import lombok.SneakyThrows;

public class Demo13 {
    @SneakyThrows
    public static void main(String[] args) {
        Thread myThread13 = new Thread(){
            private long i = 0;
            @Override
            public void run() {
                while (true){
                    i++;
                    System.out.println(i);
                }
            }
        };
        myThread13.start();
        Thread.sleep(1000);
        myThread13.suspend();
        System.out.println("main end");
    }
}
