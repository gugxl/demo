package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Demo10 {
    @SneakyThrows
    public static void main(String[] args) {
        MyOneList myOneList = new MyOneList();
        new MyThread10("A", myOneList).start();
        new MyThread10("B", myOneList).start();

        Thread.sleep(5000);
        System.out.println(myOneList.getSize());
    }


    static class MyOneList{
        private List list = new ArrayList<>();
        synchronized public void add(String data){
            list.add(data);
        }

        synchronized public int getSize(){
            return list.size();
        }
    }

    static class MyService {
        @SneakyThrows
        public MyOneList addServiceMethod(MyOneList list, String data){
            synchronized (list){
                if (list.getSize()<1){
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
            return list;
        }
    }

    static class MyThread10 extends Thread{
        private MyOneList list;

        public MyThread10(String name, MyOneList list) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            MyService myService = new MyService();
            myService.addServiceMethod(list, getName());
        }
    }

}
