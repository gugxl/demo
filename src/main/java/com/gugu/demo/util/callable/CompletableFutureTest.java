package com.gugu.demo.util.callable;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//https://mp.weixin.qq.com/s/xlVN8IjgmwGU4rEehqXK4g
public class CompletableFutureTest {
    public static void main(String[] args) {
//        completedFutureExample();
//        runAsyncExample();
//        thenApplyExample();
//        thenApplyAsyncExample();
//        thenApplyAsyncWithExecutorExample();
//        thenAcceptExample();
        thenAcceptAsyncExample();
    }


    //    创建一个完成的CompletableFuture
    public static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    //    运行一个简单的异步阶段
    @SneakyThrows
    public static void runAsyncExample() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertTrue(completableFuture.isDone());
        sleep(1200L);
        assertTrue(completableFuture.isDone());
    }

    //    在前一个阶段上应用函数
    static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    //在前一个阶段上异步应用函数
    static void thenApplyAsyncExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return s.toUpperCase();
        });

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(@NotNull Runnable r) {
            return new Thread(r, "custom-executor-" + count++);
        }
    });

    //使用定制的Executor在前一个阶段上异步应用函数
    static void thenApplyAsyncWithExecutorExample() {


        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            try {
                sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());

    }

    //    消费前一阶段的结果
    static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
            .thenAccept(s -> result.append(s));
        assertTrue(result.length() > 0, "Result was empty");

    }

    //异步地消费迁移阶段的结果
    static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue(result.length() > 0, "Result was empty");
    }


}
