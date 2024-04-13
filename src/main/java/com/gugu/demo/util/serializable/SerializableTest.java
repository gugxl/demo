package com.gugu.demo.util.serializable;


import org.apache.commons.lang3.SerializationUtils;

/**
 * @author Administrator
 * @Classname SerializableTest
 * @Description TODO
 * @Date 2022/5/29 16:29
 */
public class SerializableTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("gugu");
        user.setAddress("杭州");
        byte[] serializeUser = SerializationUtils.serialize(user);
        User deserializeUser = SerializationUtils.deserialize(serializeUser);
        System.out.println(deserializeUser);
    }


}
