package com.gugu.demo.book.book1.chapter2;

public class VolatileDemo {
    private int a = 0;

    private void addTo20() {
        a += 20;
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            volatileDemo.addTo20();
            System.out.println(Thread.currentThread().getName()+" run over + a=" + volatileDemo.a);

        }, "AA").start();

        while (volatileDemo.a == 0){

        }

        System.out.println(Thread.currentThread().getName()+" run over + a=" + volatileDemo.a);



    }
}
