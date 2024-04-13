package com.gugu.demo.book.book1.chapter3;

import java.util.ArrayList;
import java.util.List;

public class Demo11 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        // 生产线程数量大于消费线程就会栈内堆积
        new PThread(new P(myStack)).start();
        new PThread(new P(myStack)).start();
        // 但是如果多个消费数量就会产异常索引 原因是push中的if判断条件可能没有及时响应，可以把if改成while解决
        new CThread(new C(myStack)).start();
    }

    static class MyStack {
        private List list = new ArrayList<>();

        synchronized public void push() {
            try {
                while (list.size() == 1) {
                    this.wait();
                }
                list.add("anyString" + Math.random());
                this.notifyAll();
                System.out.println("push=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public String pop() {
            String returnValue = "";
            try {
                while (list.size() == 0) {
                    System.out.println("pop操作中的" + Thread.currentThread().getName() + "线程wait状态");
                    this.wait();
                }
                returnValue = "" + list.get(0);
                list.remove(0);
                this.notifyAll();
                System.out.println("pop=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return returnValue;
        }
    }

    static class P {
        private MyStack myStack;

        public P(MyStack myStack) {
            this.myStack = myStack;
        }

        public void pushService() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            myStack.push();
        }
    }

    static class C {
        private MyStack myStack;

        public C(MyStack myStack) {
            this.myStack = myStack;
        }

        public void popService() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println("pop=" + myStack.pop());
        }
    }

    static class PThread extends Thread {
        private P p;

        public PThread(P p) {
            this.p = p;
        }

        @Override
        public void run() {
            while (true) p.pushService();
        }
    }

    static class CThread extends Thread {
        private C c;

        public CThread(C c) {
            this.c = c;
        }

        @Override
        public void run() {
            while (true) c.popService();
        }
    }
}
