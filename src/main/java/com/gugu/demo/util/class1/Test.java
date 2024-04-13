package com.gugu.demo.util.class1;


/**
 * @author Administrator
 * @Classname Test
 * @Description TODO
 * @Date 2021/10/16 13:17
 */
public class Test {
    public static void main(String[] args) {
        Bclass.test();
        System.out.println(test());
    }

    public static int test() {
        int x = 1;
        try {
            return x;
        }finally {
            x++;
            return x;
        }
    }
}
