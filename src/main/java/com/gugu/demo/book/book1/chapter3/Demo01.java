package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {

    public static void main(String[] args) {
        MyList myList = new MyList();
        new ThreadA("AAA", myList).start();
        new ThreadB("BBB", myList).start();
    }
    static class MyList {
        private List list = new ArrayList<String>();

        public void add() {
            list.add("gugu");
        }

         public int size(){
            return list.size();
        }


    }

    static class ThreadA extends Thread {
        private MyList list;

        public ThreadA(@NotNull String name, MyList list) {
            super(name);
            this.list = list;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                list.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                Thread.sleep(100);
            }
            System.out.println("线程A结束");
        }
    }

    static class ThreadB extends Thread {
        private MyList list;

        public ThreadB(@NotNull String name, MyList list) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            try {
                while (true){
                    //这里需要同步一下，不然jvm优化不会进入 == 5
                    System.out.println(list.size());
                    if (list.size() == 5) {
                        System.out.println("==5 线程退出");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程B结束");


        }
    }
}
