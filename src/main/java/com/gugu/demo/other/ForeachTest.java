package com.gugu.demo.other;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname ForeachTest
 * @Description 用于演示阿里巴巴规范，禁止在foreach中remove/add元素
 * @Date 2021/6/27 10:17
 */
public class ForeachTest {
    static List<String> userNames = new ArrayList<String>(){{
        add("小谷");
        add("xiaogu");
        add("hello 小谷");
        add("XG");

    }};
    public static void main(String[] args) {
//        testUse();
//        testRemove();
//        testRemoveForeach();
//        testRemoveForeach2();
//        testIterator();
//        testStream();
//        testConcurrentLinkedDeque();
        testRemoveForeach3();
    }

    public static void testUse() {
        // 使用ImmutableList初始化一个List， 注意此处如果使用Arrays.asList()创建的"List"是不可变的，在add/remove会直接抛出异常
        List<String> userNames = ImmutableList.of("小谷", "xiaogu", "hello 小谷", "XG");

        System.out.println("使用for循环遍历List");
        for (int i = 0; i < userNames.size(); i++) {
            System.out.println(userNames.get(i));
        }

        System.out.println("使用foreach遍历List");
        for (String userName : userNames) {
            System.out.println(userName);
        }
    }

    public static void testRemove() {
        for (int i = 0; i < userNames.size(); i++) {
            if (userNames.get(i).equals("xiaogu")) {
                userNames.remove(i);
            }
        }
        System.out.println(userNames);
    }

    public static void testRemoveForeach() {
        for (String userName : userNames) {
            if (userName.equals("xiaogu")) {
                userNames.remove(userName);
            }
        }
        System.out.println(userNames);
    }

    public static void testRemoveForeach2() {
        Iterator var0 = userNames.iterator();

        while(var0.hasNext()) {
            String var1 = (String)var0.next();
            if (var1.equals("xiaogu")) {
                userNames.remove(var1);
            }
        }

        System.out.println(userNames);
    }

    public static void testIterator(){
        Iterator iterator = userNames.iterator();

        while (iterator.hasNext()){
            if (iterator.next().equals("xiaogu")){
                iterator.remove();
            }
        }
        System.out.println(userNames);
    }

    public static void testStream(){
        List<String> userNames = ForeachTest.userNames.stream().filter(userName -> !userName.equals("xiaogu")).collect(Collectors.toList());
        System.out.println(userNames);
    }

    public static void testConcurrentLinkedDeque() {
        ConcurrentLinkedDeque<String> userNames = new ConcurrentLinkedDeque<>(ForeachTest.userNames);
        for (String userName : userNames) {
            if (userName.equals("xiaogu")) {
                userNames.remove(userName);
            }
        }
        System.out.println(userNames);
    }

    public static void testRemoveForeach3() {
        for (String userName : userNames) {
            if (userName.equals("xiaogu")) {
                userNames.remove(userName);
                break;
            }
        }
        System.out.println(userNames);
    }
}


