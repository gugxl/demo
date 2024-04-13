package com.gugu.demo.util.lambda;

import com.google.common.collect.Sets;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.HashSet;

public class ParallelStreamTest {
    public static void main(String[] args) {
        ArrayList<Integer> lists = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            lists.add(i);
        }

        HashSet<String> sequenceThreadNameSet  = Sets.newHashSet();
        HashSet<String> parallelThreadNameSet  = Sets.newHashSet();

        lists.forEach(e -> sequenceThreadNameSet.add(Thread.currentThread().getName()));
        lists.parallelStream().forEach(e -> parallelThreadNameSet.add(Thread.currentThread().getName()));

        System.out.println(sequenceThreadNameSet);
        System.out.println(parallelThreadNameSet);

    }
}
