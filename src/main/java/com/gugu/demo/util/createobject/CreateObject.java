package com.gugu.demo.util.createobject;

import com.alibaba.fastjson2.JSON;

import java.lang.reflect.Constructor;

/**
 * @author Administrator
 * @Classname CreateObject
 * @Description TODO 创建对象的几种方式
 * @Date 2022/6/1 22:54
 */
public class CreateObject {
    public static void main(String[] args) throws Exception {
        User user1 = new User(); // 1. 直接new
        user1.setUserName("user1");
        System.out.println(user1);

        User user2 = (User) Class.forName("com.gugu.demo.util.createobject.User").newInstance();// 2.使用Class类的newInstance方法
        user2.setUserName("user2");
        System.out.println(user2);

        Constructor<User> constructor = User.class.getConstructor();
        User user3 = constructor.newInstance(); // 3. 使用Constructor类的newInstance方法
        user3.setUserName("user3");
        System.out.println(user3);

        User user4 = (User) user2.clone(); // 4. 使用clone方法，但是类要实现Cloneable以及 重写clone方法
        user4.setUserName("user4");
        System.out.println(user4);

        User user5 = (User) JSON.parseObject(user1.toString(), User.class); // 5. 借助其他工具类进行序列化反序列化如json
        user5.setUserName("user5");
        System.out.println(user5);

    }
}
