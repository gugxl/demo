package com.gugu.demo.video.highconcurrency.day01;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 饥饿死锁
 * https://mp.weixin.qq.com/s?__biz=MzA5MTkxMDQ4MQ==&mid=2648933019&idx=1&sn=3455877c451de9c61f8391ffdc1eb01d&chksm=88621aa5bf1593b377e2f090bf37c87ba60081fb782b2371b5f875e4a6cadc3f92ff6d747e32&token=2041017112&lang=zh_CN&scene=21#wechat_redirect
 */
public class Demo02 {
    static ExecutorService single = Executors.newSingleThreadExecutor();

    @SneakyThrows
    public static void main(String[] args) {
        MyCallable task = new MyCallable();
        Future<String> submit = single.submit(task);
        System.out.println(submit.get());
        System.out.println("over");
        single.shutdown();

    }

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("in MyCallable");
            Future<String> future = single.submit(new AnotherCallable());
            return "success:" + future.get();
        }
    }

    static class AnotherCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("in AnotherCallable");
            return "annother success";
        }
    }
}
