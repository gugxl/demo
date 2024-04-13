package com.gugu.demo.book.book1.chapter2;

import com.gugu.demo.book.book1.chapter2.Demo19.OutClass.InncClass1;
import com.gugu.demo.book.book1.chapter2.Demo19.OutClass.InncClass2;
import lombok.SneakyThrows;

// T1和T3锁的对象都是InncClass2.class所以T1和T3是同步的，和T2是异步的
public class Demo19 {
    public static void main(String[] args) {
        InncClass1 inncClass1 = new InncClass1();
        InncClass2 inncClass2 = new InncClass2();
        new Thread(() -> {
            inncClass1.method1(inncClass2);
        }, "T1").start();
        new Thread(() -> {
            inncClass1.method2();
        }, "T2").start();
        new Thread(() -> {
            inncClass2.method1();
        }, "T3").start();
    }

    static class OutClass {
        static class InncClass1 {
            @SneakyThrows
            public void method1(InncClass2 inncClass2) {
                String name = Thread.currentThread().getName();
                synchronized (inncClass2) {
                    System.out.println(name + " 进入InncClass1类中的method1方法");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("i=" + i);
                        Thread.sleep(100);
                    }
                    System.out.println(name + " 离开InncClass1类中的method1方法");

                }
            }

            @SneakyThrows
            synchronized public void method2() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " 进入InncClass1类中的method2方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println("j=" + i);
                    Thread.sleep(200);
                }
                System.out.println(name + " 离开InncClass1类中的method2方法");

            }

        }

        static class InncClass2 {
            @SneakyThrows
            synchronized public void method1() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " 进入InncClass2类中的method1方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println("k=" + i);
                    Thread.sleep(100);
                }
                System.out.println(name + " 离开InncClass2类中的method1方法");

            }
        }
    }

}
