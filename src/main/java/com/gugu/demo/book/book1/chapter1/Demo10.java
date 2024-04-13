package com.gugu.demo.book.book1.chapter1;

import lombok.Data;
import lombok.SneakyThrows;

public class Demo10 {
    @SneakyThrows
    public static void main(String[] args) {
        SyncObject syncObject = new SyncObject();
        Runnable runnable = new Runnable() {
            private SyncObject object;
            private Runnable setSyncObject(SyncObject object){
                this.object = object;
                return this;
            }
            @Override
            public void run() {
                object.printString("b", "bb");
            }
        }.setSyncObject(syncObject);


//        MyThread10 myThread10 = new MyThread10(syncObject);
        Thread myThread10 = new Thread(runnable);
        myThread10.start();
        Thread.sleep(1000);
        myThread10.stop();
        System.out.println(syncObject.getUsername() + "," + syncObject.getPassword());
    }
    @Data
    static class SyncObject{
        private String username = "a";
        private String password = "aa";

        @SneakyThrows
        synchronized public void printString(String username, String password){
            this.username= username;
            Thread.sleep(100000);
            this.password= password;

        }
    }

//    static class MyThread10 extends Thread {
//        private SyncObject object;
//
//        public MyThread10(SyncObject object) {
//            this.object = object;
//        }
//
//        @Override
//        public void run() {
//            object.printString("b", "bb");
//        }
//    }
}


