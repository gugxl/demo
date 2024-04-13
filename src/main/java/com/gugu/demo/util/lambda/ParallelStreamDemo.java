package com.gugu.demo.util.lambda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname ParallelStreamDemo
 * @Description TODO
 * @Date 2021/8/28 8:12
 */
public class ParallelStreamDemo {
    private static int size = 1000000;

    public static void main(String[] args) {
        System.out.println("--------------List");
        testList();
        System.out.println("--------------Set");
        testSet();
    }

    public static void testList() {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
        }

        List<Integer> temp = new ArrayList<>(size);

        // 老方式
        long start = System.currentTimeMillis();
        for (Integer i : list){
            temp.add(i);
        }
        System.out.println(System.currentTimeMillis() - start);

        // 同步
        long start2 = System.currentTimeMillis();
        list.stream().collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - start2);

        // 并发
        long start3 = System.currentTimeMillis();
        list.parallelStream().collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - start3);
    }

    public static void testSet() {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
        }

        Set<Integer> temp = new HashSet<>();
        // 老方式
        long start = System.currentTimeMillis();
        for (Integer i : list){
            temp.add(i);
        }
        System.out.println(System.currentTimeMillis() - start);

        // 同步
        long start2 = System.currentTimeMillis();
        list.stream().collect(Collectors.toSet());
        System.out.println(System.currentTimeMillis() - start2);

        // 并发
        long start3 = System.currentTimeMillis();
        list.parallelStream().collect(Collectors.toSet());
        System.out.println(System.currentTimeMillis() - start3);
    }

}
