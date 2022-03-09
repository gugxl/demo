package com.gugu.demo.bug;

/**
 * @author Administrator
 * @Classname Null
 * @Description TODO
 * @Date 2022/3/6 17:53
 */
public class Null {
    public static void greet() {
        System.out.println("hello world");
    }
    public static void main(String[] args) {
        Null x = null;
        x.greet();
        ((Null)x).greet();
        ((Null)null).greet();
    }
}
