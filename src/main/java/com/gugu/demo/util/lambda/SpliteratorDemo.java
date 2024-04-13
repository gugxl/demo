package com.gugu.demo.util.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

/**
 * @author gugu
 * @Classname SpliteratorDemo
 * @Description TODO
 * @Date 2022/6/11 11:38
 */
public class SpliteratorDemo {
    public static void main(String[] args) {
        List<String> lists = Arrays.asList("A", "B", "C", "D");
        Spliterator<String> spliterator = lists.stream().spliterator();
//        while (spliterator.tryAdvance(System.out::print));
//        lists.stream().spliterator().forEachRemaining(System.out::print);

        Spliterator<String> stringSpliterator = spliterator.trySplit();
        if (stringSpliterator != null){
            stringSpliterator.forEachRemaining(System.out::print);
        }
        System.out.println("----------");
        spliterator.forEachRemaining(System.out::print);
    }
}
