package com.gugu.demo.bug;

/**
 * @author Administrator
 * @Classname Test
 * @Description TODO
 * @Date 2022/3/5 14:41
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new StringBuffer().append('A').append('B'));
        System.out.println(new StringBuffer('A').append('B'));
        System.out.println(new StringBuffer("A").append("B"));
        String s = "who";
        System.out.println("who" == s);
        System.out.println("who" == "who");
        System.out.println("who" == new String("who"));
        System.out.println("who" == new String("who").intern());
    }
}
