package com.gugu.demo.sugar;

import com.google.common.collect.ImmutableList;

/**
 * @author Administrator
 * @Classname ForeachTest
 * @Description TODO
 * @Date 2022/2/21 23:14
 */
public class ForeachTest {
    public static void main(String[] args) {
        String[] strs = {"gugu", "xiaogu", "博客：https://blog.csdn.net/zhazhagu"};
        for (String str : strs) {
            System.out.println(str);
        }
        ImmutableList<String> strList = ImmutableList.of("gugu", "xiaogu", "博客：https://blog.csdn.net/zhazhagu");
        for (String s : strList) {
            System.out.println(s);
        }
    }
}
