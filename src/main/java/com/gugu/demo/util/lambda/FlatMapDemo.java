package com.gugu.demo.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @Classname FlatMapDemo
 * @Description TODO
 * @Date 2021/8/19 0:02
 */
public class FlatMapDemo {
    public static void flatMapString() {
        List<PersonModel> data = Data.getData();
        List<String> collect = data.stream().flatMap(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());
        System.out.println(collect);
        List<Stream<String>> collect1 = data.stream().map(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());
        System.out.println(collect1);
        List<String> collect2 = data.stream().map(person -> person.getName().split(" ")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(collect2);
        List<String> collect3 = data.stream().map(person -> person.getName().split(" ")).flatMap(str -> Arrays.asList(str).stream()).collect(Collectors.toList());
        System.out.println(collect3);
    }

    public static void main(String[] args) {
        flatMapString();
    }
}
