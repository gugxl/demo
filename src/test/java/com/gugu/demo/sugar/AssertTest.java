package com.gugu.demo.sugar;

/**
 * @author Administrator
 * @Classname AssertTest
 * @Description TODO
 * @Date 2022/2/19 17:26
 */
public class AssertTest {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        assert a == b;
        System.out.println("gugu");
        assert a != b : "xiaogu";
        System.out.println("博客：https://blog.csdn.net/zhazhagu");
    }
}
