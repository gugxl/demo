package com.gugu.demo.util.lambda;

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
        // map_flatMap2();
        // map_flatMap3();
        // reduce();
        // reduce2();
        // collect_toList();
        // collect_count_averaging();
        // partitioningBy_groupingBy();
        // joiningTest();
        // reducingTest();
        // sorted();
        distict_skip_limit();
    }

    private static void distict_skip_limit() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);

        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }

    private static void sorted() {
        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
            .collect(Collectors.toList());

        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
            .map(Person::getName).collect(Collectors.toList());

        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
            .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
            .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((x1, x2) -> {
            if (x1.getSalary() == x2.getSalary()) {
                return x2.getAge() - x1.getAge();
            } else {
                return x2.getSalary() - x1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);

    }

    private static void reducingTest() {
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());

    }

    private static void joiningTest() {
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
    }


    private static void partitioningBy_groupingBy() {
        Map<Boolean, List<Person>> part =
            personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        Map<String, Map<String, List<Person>>> group2 =
            personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }


    private static void collect_count_averaging() {
        Long count = personList.stream().collect(Collectors.counting());
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);

    }

    private static void collect_toList() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        Map<String, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
            .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);

    }

    private static void reduce2() {
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
            (sum1, sum2) -> sum1 + sum2);
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
            Integer::max);
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
            (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);


    }

    private static void reduce() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum3 = list.stream().reduce(0, Integer::sum);
        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
    }

    private static void map_flatMap3() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> Arrays.stream(s.split(","))).collect(Collectors.toList());
        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    private static void map_flatMap2() {
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
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
