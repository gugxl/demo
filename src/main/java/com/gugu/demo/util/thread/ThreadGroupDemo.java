package com.gugu.demo.util.thread;

/**
 * @author gugu
 * @Classname ThreadGroupDemo
 * @Description TODO
 * @Date 2022/6/3 21:02
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("线程线程组名称："+ Thread.currentThread().getThreadGroup().getName());
            System.out.println("线程名称："+ Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ThreadGroup userThreadGroup = new ThreadGroup("user");
        userThreadGroup.setMaxPriority(Thread.MIN_PRIORITY);
        Thread userTask1 = new Thread(userThreadGroup, runnable, "user-task1");
        Thread userTask2 = new Thread(userThreadGroup, runnable, "user-task2");

        userTask1.start();
        userTask2.start();

        System.out.println("线程组活跃线程数量："+ userThreadGroup.activeCount());
        userThreadGroup.list();
    }
}
