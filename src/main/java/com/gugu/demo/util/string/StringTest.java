package com.gugu.demo.util.string;

public class StringTest {
    public static void main(String[] args) {
        String s1 = new String("二哥") + new String("三妹");
        String s3 = new String("二哥三妹").toString();
        String s2 = s1.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
    }
}
