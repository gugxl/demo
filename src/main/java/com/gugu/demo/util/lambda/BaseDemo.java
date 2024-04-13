package com.gugu.demo.util.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author gugu
 * @Classname BaseDemo
 * @Description TODO
 * @Date 2022/6/6 23:37
 */
public class BaseDemo {
    public static void main(String[] args) {
        learnStream();
    }

    public static void learnStream() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(3);
        list.add(7);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(8);
        System.out.println("原始数据");
        list.stream().forEach(x -> System.out.print(x + " "));
        //最小值
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        if (min.isPresent()){
            System.out.println("\nList中最小的值为:" + min.get());
        }

        //最大值
        System.out.print("List中最大的值为:");
        list.stream().max(Integer::compareTo).ifPresent(System.out::println);

        //排序
        list.stream().sorted().forEach(x -> System.out.print(x + " "));

        // 过滤
        System.out.println("\n获取大于3的：");
        list.stream().filter(x -> (x > 3)).forEach(x -> System.out.print(x + " "));

    }
}
