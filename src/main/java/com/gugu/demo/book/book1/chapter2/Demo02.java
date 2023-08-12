package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        PublicVar publicVar = new PublicVar();
        Thread02 thread02 = new Thread02(publicVar);
        thread02.start();
        // 打印结果和这个有关,会脏读
        Thread.sleep(500);
        publicVar.getValue();
    }


    static class PublicVar{
        public String name;
        private String pass;

        @SneakyThrows
        synchronized public void setValue(String name, String pass){
            this.name = name;
            Thread.sleep(1000);
            this.pass = pass;
            System.out.println("set value method name = "+ Thread.currentThread().getName() + " ,name =" + name + ", pass=" + pass);
        }

        public void getValue(){
            System.out.println("get value method name = "+ Thread.currentThread().getName() + " ,name =" + name + ", pass=" + pass);

        }
    }

    static class Thread02 extends Thread{
        public PublicVar publicVar;

        public Thread02(PublicVar publicVar) {
            this.publicVar = publicVar;
        }

        @Override
        public void run() {
            publicVar.setValue("B", "BB");
        }
    }
}
