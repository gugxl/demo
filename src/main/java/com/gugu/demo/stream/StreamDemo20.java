package com.gugu.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 * 20 个超级使用的 Java 8 Stream，玩转集合的筛选、归约、分组、聚合
 * https://mp.weixin.qq.com/s/ytQNOHkpdLWxQxCnfN3SRQ
 */
public class StreamDemo20 {


    public static void main(String[] args) {
        // createStream();
        // foreach_find_match();
        // filter();
        // max_count();
        // map_flatMap();

        // List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // numbers.parallelStream().forEachOrdered(out::println);
    }



    private static void map_flatMap() {
        // 1.英文字符串数组的元素全部改为大写
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        out.println("每个元素大写：" + strList);
        // 2.整数数组每个元素+3
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        out.println("每个元素+3：" + intListNew);

    }

    private static void max_count() {
        // 1.最长的字符串
        List<String> list = Arrays.asList("weoujgsw", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        out.println("最长的字符串：" + max.get());
        // 2.获取Integer集合中的最大值
        List<Integer> list2 = Arrays.asList(7, 6, 9, 4, 11, 6);
        Optional<Integer> max1 = list2.stream().max(Integer::compareTo);
        out.println("自然排序的最大值：" + max1.get());
        Optional<Integer> max2 = list2.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        out.println("自定义排序的最大值：" + max2.get());

        // 3.获取员工工资最高的人
        Optional<Person> max3 = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        out.println("员工工资最大值：" + max3.get().getSalary());

        long count = list2.stream().filter(x -> x > 6).count();
        out.println("list2中大于6的元素个数：" + count);
    }

    public static void createStream() {
        // 1.Collection.stream()
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        stream.forEach(out::println);
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(out::println);

        // 2.Arrays.stream(T[] array)
        int[] array = {1, 3, 5, 6, 8};
        IntStream stream1 = Arrays.stream(array);
        stream1.forEach(out::println);

        // 3.Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
        stream2.forEach(out::println);

        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream3.forEach(out::println);

        Stream<Double> stream4 = Stream.generate(Math::random).limit(3);
        stream4.forEach(out::println);

    }

    private static void foreach_find_match() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        list.stream().filter(x -> x > 6).forEach(out::println);
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        out.println("匹配第一个值：" + findFirst.get());
        out.println("匹配任意一个值：" + findAny.get());
        out.println("是否存在大于6的值：" + anyMatch);
    }

    private static void filter() {
        // 1
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(out::println);
        // 2.筛选员工中工资高于8000的人
        List<String> collect =
                personList.stream().filter(x -> x.salary > 8000).map(Person::getName).collect(Collectors.toList());
        out.println("高于8000的员工姓名：" + "高于8000的员工姓名：" + collect);
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name; // 姓名
        private int salary; // 薪资
        private int age; // 年龄
        private String sex; // 性别
        private String area; // 地区
    }

    static List<Person> personList = new ArrayList<Person>();

    static {
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
    }

}
