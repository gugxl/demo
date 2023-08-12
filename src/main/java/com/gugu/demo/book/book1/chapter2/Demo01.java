package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo01 {

    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum1 = new HasSelfPrivateNum();
        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNum1);
        ThreadB threadB = new ThreadB(hasSelfPrivateNum2);
        threadA.start();
        threadB.start();
    }

    static class HasSelfPrivateNum {
        private int num;

        @SneakyThrows
        synchronized public void addI(String name) {
            if (name.equals("a")) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(1000);

            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(name + " num=" + num);
        }


    }

    static class ThreadA extends Thread {
        private HasSelfPrivateNum numRef;

        public ThreadA(HasSelfPrivateNum numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("a");
        }
    }

    static class ThreadB extends Thread {
        private HasSelfPrivateNum numRef;

        public ThreadB(HasSelfPrivateNum numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("b");
        }
    }

}
