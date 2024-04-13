package com.gugu.demo.util.jvm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gugu
 * @Classname JvmThread
 * @Description TODO
 * @Date 2022/8/6 8:58
 */
public class JvmThread {
    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]>  list = new ArrayList<>();
            while (true){
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                byte[] b = new byte[1024 * 1024 * 1];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true){
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
