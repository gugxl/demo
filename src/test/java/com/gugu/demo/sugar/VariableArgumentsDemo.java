package com.gugu.demo.sugar;

/**
 * @author Administrator
 * @Classname VariableArgementDemo
 * @Description TODO
 * @Date 2022/2/18 22:17
 */
public class VariableArgumentsDemo {
    public static void main(String[] args) {
        print("gugu", "xiaogu", "博客：https://blog.csdn.net/zhazhagu");
    }
    public static void print(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }
}
