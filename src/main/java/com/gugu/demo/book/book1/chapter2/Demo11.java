package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class Demo11 {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
//        MyObject myObject2 = new MyObject();
        Service11 service11 = new Service11();
//        用一个对象会同步，两个对象的话是异步执行的
        new MyThread11("A",myObject, service11).start();
        new MyThread11("B",myObject, service11).start();
    }


    static class MyObject{}

    static class Service11 {
        @SneakyThrows
        void testMethod1(MyObject myObject){
            synchronized (myObject){
                System.out.println("testmethod1 getLock time=" + System.currentTimeMillis()
                        + " run ThreadName=" + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("testmethod1 releaseLock time=" + System.currentTimeMillis()
                        + " run ThreadName=" + Thread.currentThread().getName());
            }
        }
    }


    static class MyThread11 extends Thread {
        private MyObject myObject;
        private Service11 service11;

        public MyThread11(@NotNull String name, MyObject myObject, Service11 service11) {
            super(name);
            this.myObject = myObject;
            this.service11 = service11;
        }

        @Override
        public void run() {
            service11.testMethod1(myObject);
        }
    }
}
