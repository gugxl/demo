package com.gugu.demo.jvm0522;

/**
 * @author Administrator
 * @Classname Father
 * @Description TODO
 * @Date 2021/5/22 20:14
 */
public class Father {
    public static int i = 10;
    static {
        i = 20;
    }
}
class Son extends Father{
    public static int i2 = i;
}

class Test0522{
    public static void main(String[] args) {
        System.out.println(Son.i2);
    }
}