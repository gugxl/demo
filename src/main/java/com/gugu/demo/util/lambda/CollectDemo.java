package com.gugu.demo.util.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @Classname CollectDemo
 * @Description TODO
 * @Date 2021/8/19 23:55
 */
public class CollectDemo {
    public static void main(String[] args) {
        List<PersonModel> data = Data.getData();
        List<String> collect = data.stream().map(PersonModel::getName).collect(Collectors.toList());
        System.out.println(collect);

        Set<String> collect1 = data.stream().map(PersonModel::getName).collect(Collectors.toSet());
        System.out.println(collect1);

        Map<String, Integer> collect2 = data.stream().collect(Collectors.toMap(PersonModel::getName, PersonModel::getAge));
        System.out.println(collect2);

        Map<String, String> collect3 = data.stream().collect(Collectors.toMap(per -> per.getName(), value -> {
            return value + "1";
        }));
        System.out.println(collect3);

        // 指定类型
        TreeSet<PersonModel> collect4 = data.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect4);

        // 分组
        Map<String, List<PersonModel>> collect5 = data.stream().collect(Collectors.groupingBy(PersonModel::getSex));
        System.out.println(collect5);

        // 分割
        String collect6 = data.stream().map(PersonModel::getName).collect(Collectors.joining(",", "{", "}"));
        System.out.println(collect6);

        // 自定义
        List collect7 = Stream.of("1", "2", "3").collect(Collectors.reducing(new ArrayList<String>(), x -> Arrays.asList(x), (y, z) -> {
            y.addAll(z);
            return y;
        }));
        System.out.println(collect7);
    }
}
