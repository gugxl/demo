package com.gugu.demo.book.book1.chapter3;

import lombok.SneakyThrows;

public class Demo02 {
    @SneakyThrows
    public static void main(String[] args) {
        String lock = new String();
        System.out.println("syn 上面");
        synchronized (lock) {
            System.out.println("sync 第一行");
            lock.wait();
            // 会一直等待唤醒
            System.out.println("wait 下面的代码");
        }

        System.out.println("syn 下面的代码");
    }
}
