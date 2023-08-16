package com.gugu.demo.book.book1.chapter2;

import lombok.SneakyThrows;

public class Demo30 {

    @SneakyThrows
    public static void main(String[] args) {
        Service service = new Service();
        new ThreadA(service).start();
        Thread.sleep(1000);
        new ThreadB(service).start();
        System.out.println("已经发送停止命令");
    }
    static class Service {

        private boolean isContinueRun = true;
        public void runMethod(){
//            String anyString = new String();
            while (isContinueRun){
                // 进行同步
//                synchronized (anyString){}

            }
            System.out.println("停下来了 ");

        }

        public void stopMethod(){
            isContinueRun = false;
        }


    }
    static class ThreadA extends Thread{
        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.runMethod();
        }
    }

    static class ThreadB extends Thread{
        private Service service;

        public ThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.stopMethod();
        }
    }
}
