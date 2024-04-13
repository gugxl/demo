package com.gugu.demo.util.threadlocal;

import com.gugu.demo.util.createobject.User;
import lombok.SneakyThrows;

/**
 * @author Administrator
 * @Classname ThreadLocalTest
 * @Description TODO
 * @Date 2022/6/3 10:13
 */
public class ThreadLocalTest {

    private static ThreadLocal<User> tl = new ThreadLocal<User>(){
        @Override
        protected User initialValue() {
            return new User("小谷","许昌");
        }
    };


    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new TestThread().start();
        }
        Thread.sleep(100000);
        System.out.println(Thread.currentThread().getName() + tl.get());

    }

    static class TestThread extends Thread{

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 30000; i++) {
                User user = tl.get();
                user.setUserName(Thread.currentThread().getName());
                user.setAddress(Thread.currentThread().getName());
                tl.set(user);
                System.out.println(tl.get());
            }
        }
    }
}
