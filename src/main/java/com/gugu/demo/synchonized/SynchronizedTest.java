package com.gugu.demo.synchonized;

/**
 * @author Administrator
 * @Classname SynchronizedTest
 * @Description TODO
 * @Date 2021/11/6 9:21
 */
public class SynchronizedTest {
    public static void main(String[] args) {

    }
    public synchronized void method1(){
        System.out.println("Hello world!");
    }

    public void method2(){
        synchronized (this){
            System.out.println("Hello world!");
        }
    }
}
