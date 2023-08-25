package com.gugu.demo.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Classname TestList
 * @Description TODO
 * @Date 2021/3/31 22:01
 */
public class TestList {
    public static void main(String[] args) {
        List<UserDO> a = new ArrayList<>();
        UserDO userDO;
        for (int i = 0 ; i < 10; i++){
            userDO = new UserDO(Long.valueOf(i), "gugu");
            a.add(userDO);
        }
        System.out.println(a);
    }
}
