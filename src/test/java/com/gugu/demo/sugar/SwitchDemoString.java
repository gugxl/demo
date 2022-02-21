package com.gugu.demo.sugar;

/**
 * @author Administrator
 * @Classname SwitchDemoString
 * @Description TODO
 * @Date 2022/2/18 21:47
 */
public class SwitchDemoString {
    public static void main(String[] args) {
        String str = "world";
        switch (str) {
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            default:
                break;
        }
    }
}
