package com.gugu.demo.jvm0522;

/**
 * @author Administrator
 * @Classname FatherClass
 * @Description TODO
 * @Date 2021/5/22 20:25
 */
public class FatherClass {
    static {
        System.out.println("FatherClass init");
    }
    public static final int val = 10;
}

class SonClass extends FatherClass{
    static {
        System.out.println("SonClass init");
    }
}

class Test0522_3{
    public static void main(String[] args) {
        System.out.println(SonClass.val);
    }
}

class Test0522_4{
    public static void main(String[] args) {
        SonClass[] sonClass = new SonClass[10];
        System.out.println(sonClass.length);
    }
}

class Test0522_5{
    public static void main(String[] args) {
        System.out.println(FatherClass.val);
    }
}