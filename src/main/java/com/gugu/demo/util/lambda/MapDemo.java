package com.gugu.demo.util.lambda;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname MapDemo
 * @Description TODO
 * @Date 2021/8/18 23:56
 */
public class MapDemo {
    public static void getUserNameList() {
        List<PersonModel> data = Data.getData();

        List<String> collect = data.stream().map(PersonModel::getName).collect(Collectors.toList());
        System.out.println(collect);

        List<String> collect2 = data.stream().map(person -> person.getName()).collect(Collectors.toList());
        System.out.println(collect2);

        List<String> collect3 = data.stream().map(person -> {
            System.out.print(person.getName());
            return person.getName();
        }).collect(Collectors.toList());


    }

    public static void main(String[] args) {
        getUserNameList();
    }
}
