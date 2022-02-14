package com.gugu.demo.guava;

import com.google.common.collect.ArrayListMultimap;

import java.util.Collection;

/**
 * @author Administrator
 * @Classname MultimapsTest
 * @Description TODO
 * @Date 2022/2/14 21:43
 */
public class MultimapsTest {
    public static void main(String[] args) {
        ArrayListMultimap<String, String> myMultimap = ArrayListMultimap.create();
        // 添加键值对
        myMultimap.put("Fruits", "Bannana");
        //给Fruits元素添加另一个元素
        myMultimap.put("Fruits", "Apple");
        myMultimap.put("Fruits", "Pear");
        myMultimap.put("Vegetables", "Carrot");

        // 获得multimap的size
        int size = myMultimap.size();
        System.out.println(size);  // 4

        // 获得Fruits对应的所有的值
        Collection<String> fruits = myMultimap.get("Fruits");
        System.out.println(fruits); // [Bannana, Apple, Pear]

        Collection<String> vegetables = myMultimap.get("Vegetables");
        System.out.println(vegetables); // [Carrot]

        //遍历Mutlimap
        for (String value : myMultimap.values()) {
            System.out.println(value);
        }

        // 移除单个元素
        myMultimap.remove("Fruits","Pear");
        System.out.println(myMultimap.get("Fruits")); // [Bannana, Apple]

        // 移除一个key的所有元素
        myMultimap.removeAll("Fruits");
        System.out.println(myMultimap.get("Fruits")); // [] (空集合)

    }
}
