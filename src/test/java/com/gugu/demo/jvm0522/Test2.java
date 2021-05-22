package com.gugu.demo.jvm0522;

import java.io.Serializable;

/**
 * @author Administrator
 * @Classname Test2
 * @Description TODO
 * @Date 2021/5/22 20:45
 */
public class Test2 {
//    public static void sayHello(Object arg) {
//        System.out.println("hello Object");
//    }
//    public static void sayHello(int arg) {
//        System.out.println("hello int");
//    }
//    public static void sayHello(long  arg) {
//        System.out.println("hello long");
//    }
//    public static void sayHello(Character  arg) {
//        System.out.println("hello Character ");
//    }
//    public static void sayHello(char  arg) {
//        System.out.println("hello char ");
//    }
//    public static void sayHello(char... arg) {
//        System.out.println("hello char...");
//    }
//    public static void sayHello(Serializable arg) {
//        System.out.println("hello Serializable ");
//    }
public static void sayHello(char... arg) {
    System.out.println("hello char...");
}

    public static void main(String[] args) {
        char a = 'a';
        sayHello(a);
    }
}
