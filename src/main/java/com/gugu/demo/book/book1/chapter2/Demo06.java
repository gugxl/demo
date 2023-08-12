package com.gugu.demo.book.book1.chapter2;

import org.jetbrains.annotations.NotNull;

public class Demo06 {
    public static void main(String[] args) {
        Task task = new Task();
        new MyThread06("a", task).start();
        new MyThread06("b", task).start();
    }

    static class Task {
        public void doLongTimeTask() {
            for (int i = 0; i < 100; i++) {
                System.out.println("nosynchronized threadName=" + Thread.currentThread().getName() + "  i=" + i);
            }
            System.out.println();
            synchronized (this) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("synchronized threadName=" + Thread.currentThread().getName() + "  i=" + i);
                }
            }
        }
    }

    static class MyThread06 extends Thread{
        private Task task;

        public MyThread06(@NotNull String name, Task task) {
            super(name);
            this.task = task;
        }

        @Override
        public void run() {
            task.doLongTimeTask();
        }
    }
}
