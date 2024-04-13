package com.gugu.demo.book.book1.chapter2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Demo09 {
    public static void main(String[] args) {
        MyList myList = new MyList();
        new MyThread09("A", myList).start();
        new MyThread09("B", myList).start();

    }


    static class MyList {
        private List list= new ArrayList<>();
        synchronized public void add(String userName){
            System.out.println("threadName:" + Thread.currentThread().getName() + " 执行了add方法");
            list.add(userName);
            System.out.println("threadName:" + Thread.currentThread().getName() + " 退出了add方法");
        }

        synchronized public int getSize(){
            System.out.println("threadName:" + Thread.currentThread().getName() + " 执行了getSize方法");
            int size = list.size();
            System.out.println("threadName:" + Thread.currentThread().getName() + " 退出了getSize方法");
            return size;
        }

    }

    static class MyThread09 extends Thread{
        private MyList list;

        public MyThread09(@NotNull String name, MyList list) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                list.add(getName() + i);

            }
        }
    }

}
