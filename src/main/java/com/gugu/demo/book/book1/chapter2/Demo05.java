package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo05 {
    public static void main(String[] args) {
        Sub05 sub05 = new Sub05();
        MyThread05 a = new MyThread05(sub05);
        a.setName("A");
        a.start();
        MyThread05 b = new MyThread05(sub05);
        b.setName("B");
        b.start();

    }


    static class Main05 {
        @SneakyThrows
        synchronized public void serviceMethod() {
            System.out.println(" int main 下一步 sleep begin threadname = " + Thread.currentThread().getName()
                    + "time= " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(" int main 下一步 sleep end threadName= " + Thread.currentThread().getName()
                    + " time =" + System.currentTimeMillis());
        }
    }

    static class Sub05 extends Main05 {
        @SneakyThrows
        @Override
        // synchronized子类不会从父类中继承synchronized
        public void serviceMethod() {
            System.out.println(" int sub 下一步 sleep begin threadname = " + Thread.currentThread().getName()
                    + " time= " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(" int sub 下一步 sleep end threadName= " + Thread.currentThread().getName()
                    + " time =" + System.currentTimeMillis());

            super.serviceMethod();
        }
    }

    static class MyThread05 extends Thread {
        private Sub05 sub05;

        public MyThread05(Sub05 sub05) {
            this.sub05 = sub05;
        }

        @Override
        public void run() {
            sub05.serviceMethod();
        }
    }

}
