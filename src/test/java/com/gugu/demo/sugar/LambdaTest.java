package com.gugu.demo.sugar;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname LambdaTest
 * @Description TODO
 * @Date 2022/2/21 23:48
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<String> strList = ImmutableList.of("gugu", "xiaogu", "博客：https://blog.csdn.net/zhazhagu");
//        strList.forEach(s -> {
//            System.out.println(s);
//        });
        List HollisList = strList.stream().filter(string -> string.contains("gu")).collect(Collectors.toList());
        HollisList.forEach( s -> { System.out.println(s); } );

    }
}
