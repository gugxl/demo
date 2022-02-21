package com.gugu.demo.sugar;

import java.util.List;

/**
 * @author Administrator
 * @Classname GenericTypes
 * @Description TODO
 * @Date 2022/2/21 23:53
 */
public class GenericTypes {
    // 语法糖 泛型 + 重载
//    public static void method(List<String> list) {
//        System.out.println("invoke method(List<String> list)");
//    }

    public static void method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
    }
}
