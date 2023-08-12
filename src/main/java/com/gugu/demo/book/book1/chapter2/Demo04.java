package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo04 {
    @SneakyThrows
    public static void main(String[] args) {
        Service04 service = new Service04();

        ThreadA04 threadA04 = new ThreadA04(service);
        threadA04.setName("a");
        threadA04.start();

        Thread.sleep(1);
        ThreadA04 threadB = new ThreadA04(service);
        threadB.setName("b");
        threadB.start();

    }


    static class ThreadA04 extends Thread {
        Service04 service04;

        public ThreadA04(Service04 service04) {
            this.service04 = service04;
        }

        @Override
        public void run() {
            service04.testMethod();
        }
    }


    static class Service04 {
        synchronized public void testMethod() {
            if (Thread.currentThread().getName().equals("a")) {
                System.out.println("ThreadName=" + Thread.currentThread().getName() + " run begin " + System.currentTimeMillis());
                int i = 1;
                while (i == 1) {
                    if ((Math.random() + "").substring(0, 8).equals("0.123456")) {
                        System.out.println("ThreadName=" + Thread.currentThread().getName() + " run exception time " + System.currentTimeMillis());
                        Integer.parseInt("a");
                    } else {
                        System.out.println("Thread B run time=" + System.currentTimeMillis());
                    }
                }
            }
        }
    }


}
