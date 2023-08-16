package com.gugu.demo.book.book1.chapter2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

// 只要對象不变，即使对象内容发生改变，两个也是同步的
public class Demo21 {
    @SneakyThrows
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("gugu");
        Service service = new Service();
        new MyThread("A", userInfo, service).start();
        Thread.sleep(100);
        new MyThread("B", userInfo, service).start();
    }
    @Data
    @AllArgsConstructor
    static class UserInfo{
        private String userName;
    }

    static class Service{
        @SneakyThrows
        public void method1(UserInfo userInfo){
            synchronized (userInfo){
                System.out.println(Thread.currentThread().getName());
                userInfo.setUserName("gxl");
                Thread.sleep(1000);
                System.out.println("end!" + System.currentTimeMillis());
            }
        }


    }

    static class MyThread extends Thread{
        private UserInfo userInfo;
        private Service service;

        public MyThread(@NotNull String name, UserInfo userInfo, Service service) {
            super(name);
            this.userInfo = userInfo;
            this.service = service;
        }

        @Override
        public void run() {
            service.method1(userInfo);
        }
    }




}
