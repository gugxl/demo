package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo08 {
    public static void main(String[] args) {
        Service08 service08 = new Service08();
        new ThreadA08(service08).start();
        new ThreadB08(service08).start();
    }


    static class Service08 {
        private String anyString = new String();

        @SneakyThrows
        void a() {
            synchronized (anyString) {
                System.out.println("a begin");
                Thread.sleep(1000);
                System.out.println("a end");
            }
        }

        @SneakyThrows
        synchronized void b() {
            System.out.println("b start");
            System.out.println("b end");
        }
    }

    static class ThreadA08 extends Thread {
        private Service08 service08;

        public ThreadA08(Service08 service08) {
            this.service08 = service08;
        }

        @Override
        public void run() {
            service08.a();
        }
    }

    static class ThreadB08 extends Thread {
        private Service08 service08;

        public ThreadB08(Service08 service08) {
            this.service08 = service08;
        }

        @Override
        public void run() {
            service08.b();
        }
    }
}
