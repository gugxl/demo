package com.gugu.demo.util.createobject;

/**
 * @author gugu
 * @Classname TestClone
 * @Description TODO
 * @Date 2023/4/10 22:32
 */
public class TestClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User();
        User user1 = (User) user.clone();
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
    }
}
