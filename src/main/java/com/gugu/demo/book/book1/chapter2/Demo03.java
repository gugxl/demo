package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo03 {
    public static void main(String[] args) {

        new Thread(() -> {
            new Service().service1();
        }).start();

        new Thread(() -> {
            new Sub().operateInSubMathod();
        }).start();
    }

    static class Service {
        //    synchronized 是可重入的
        synchronized public void service1() {
            System.out.println("service1");
            service2();
        }

        synchronized public void service2() {
            System.out.println("service2");
            service3();
        }

        synchronized public void service3() {
            System.out.println("service3");
        }
    }


    static class Main {
        int i = 10;

        @SneakyThrows
        synchronized public void operateInMainMathod() {
            i--;
            System.out.println("main operate i=" + i);
            Thread.sleep(100);

        }

    }

    static class Sub extends Main {
        @SneakyThrows
        synchronized public void operateInSubMathod() {
            while (i > 0) {
                i--;
                System.out.println("sub operate i = " + i);
                Thread.sleep(100);
                // 子类可以重入父类的同步方法
                this.operateInMainMathod();
            }

        }
    }


}

