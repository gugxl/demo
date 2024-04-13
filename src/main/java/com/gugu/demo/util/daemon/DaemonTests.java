package com.gugu.demo.util.daemon;

import lombok.SneakyThrows;

/**
 * @author Administrator
 * @Classname DaemonTests
 * @Description TODO
 * @Date 2022/6/3 9:56
 */
public class DaemonTests {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(() -> execute());
        thread.setDaemon(true);
        thread.start();
        System.in.read();
    }

    public static void execute(){
        for (int i = 0;; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}
