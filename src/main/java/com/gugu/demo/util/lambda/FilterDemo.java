package com.gugu.demo.util.lambda;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname FilterDemo
 * @Description TODO
 * @Date 2021/8/18 23:42
 */
public class FilterDemo {

    public static void filterSex() {
        List<PersonModel> data = Data.getData();
        List<PersonModel> collect = data.stream().filter(person -> "男".equals(person.getSex())).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void filterSexAndAge() {
        List<PersonModel> data = Data.getData();
        List<PersonModel> collect = data.stream().filter(person ->
        {
            if ("男".equals(person.getSex()) && 20 > person.getAge()) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void filterSexAndAge2() {
        List<PersonModel> data = Data.getData();
        List<PersonModel> collect = data.stream().filter(person -> "男".equals(person.getSex()) && 20 > person.getAge()).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void main(String[] args) {
//        filterSex();
//        filterSexAndAge();
        filterSexAndAge2();
    }
}
