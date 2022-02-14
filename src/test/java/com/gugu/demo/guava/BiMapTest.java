package com.gugu.demo.guava;

import com.google.common.collect.HashBiMap;

/**
 * @author Administrator
 * @Classname BiMapTests
 * @Description TODO
 * @Date 2022/2/14 21:41
 */
public class BiMapTest {
    public static void main(String[] args) {
        HashBiMap<String, String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一", "Monday");
        weekNameMap.put("星期二", "Tuesday");
        weekNameMap.put("星期三", "Wednesday");
        weekNameMap.put("星期四", "Thursday");
        weekNameMap.put("星期五", "Friday");
        weekNameMap.put("星期六", "Saturday");
        weekNameMap.put("星期日", "Sunday");

        System.out.println("星期日的英文名是" + weekNameMap.get("星期日"));
        System.out.println("Sunday的中文是" + weekNameMap.inverse().get("Sunday"));
    }
}
