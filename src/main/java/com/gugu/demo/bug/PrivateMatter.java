package com.gugu.demo.bug;

/**
 * @author Administrator
 * @Classname PrivateMatter
 * @Description TODO
 * @Date 2022/3/6 19:23
 */
public class PrivateMatter {
    public static void main(String[] args) {
        System.out.println(new Child().name);
    }
}
class Parent{
    public String name = "Parent";
}
class Child extends Parent{
    public String name = "Child";
}