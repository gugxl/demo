package com.gugu.demo.video.highconcurrency.day08;

public class Demo03 {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        System.out.println(thread.getThreadGroup());
        System.out.println(thread.getThreadGroup().getParent());
        System.out.println(thread.getThreadGroup().getParent().getParent());
    }
}
