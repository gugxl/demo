package com.gugu.demo.util.thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Classname TestArrayList
 * @Description TODO
 * @Date 2022/6/3 16:08
 */
public class TestArrayList {
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            testList();
            list.clear();
        }
    }

    @SneakyThrows
    public static void testList() {
        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread.start();
        thread1.start();
        thread2.start();

        thread.join();
        thread1.join();
        thread2.join();
        System.out.println("size:" + list.size());
    }
}
