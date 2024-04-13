package com.gugu.demo.util.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapForeach {
    // 1. 使用 Iterator 遍历 HashMap EntrySet
    static Map<Integer, String> coursesMap = new HashMap<Integer, String>();

    static {
        coursesMap.put(1, "C");
        coursesMap.put(2, "C++");
        coursesMap.put(3, "Java");
        coursesMap.put(4, "Spring Framework");
        coursesMap.put(5, "Hibernate ORM framework");
    }

    public static void main(String[] args) {
        Set<Map.Entry<Integer, String>> entries = coursesMap.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        for (Integer integer : coursesMap.keySet()) {
            System.out.println(integer);
            System.out.println(coursesMap.get(integer));
        }

        coursesMap.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        coursesMap.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }
}
