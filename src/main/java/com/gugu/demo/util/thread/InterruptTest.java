package com.gugu.demo.util.thread;

import lombok.SneakyThrows;

/**
 * @author gugu
 * @Classname InterruptTest
 * @Description TODO
 * @Date 2022/6/3 16:39
 */
public class InterruptTest {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();

    }

    /**
     * @return void
     * @Description 不会中断，因为虽然给线程发出了中断信号，但程序中并没有响应中断信号的逻辑，所以程序不会有任何反应。
     * @params
     * @auther gugu
     */

    public static void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });
        thread.start();
        thread.interrupt();
    }

    // 正常中断
    public static void test2() {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    return;
                }
            }
        });
        thread.start();
        thread.interrupt();
    }

    /**
     * @return void
     * @Description 中断失败， sleep方法被中断后会清除中断标记，所以循环会继续运行。。
     * @params
     * @auther gugu
     */

    @SneakyThrows
    public static void test3() {
        Thread thread = new Thread(() -> {
            while (true) {
                // 相应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    return;
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("休眠线程被中断");
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    /**
     * @Description 正常中断，中断状态被清除后，再次触发中断
     * @params
     * @return void
     * @auther gugu
     */
    
    @SneakyThrows
    public static void test4() {
        Thread thread = new Thread(() -> {
            while (true) {
                // 相应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    return;
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("休眠线程被中断");
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}