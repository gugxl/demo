package com.gugu.demo.util.leak;

public class LeakTest {
    public void test() {
        int i = 8;
        while ((i = i - 3) > 0) ;
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
        LeakTest hello = new LeakTest();
        for (int i = 0; i < 50_000; i++) {
            hello.test();
        }
    }

}
