package com.gugu.demo.util.lambda;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WorkTest {
    /**
     * a and b
     */
    @org.junit.jupiter.api.Test
    public void testWork1() {
        List<String> list1 = Lists.newArrayList("a", "b", "ab");
        List<String> list2 = Lists.newArrayList("a", "c", "ab");

        List<String> result =
            list1.stream().filter(x -> list2.stream().anyMatch(e -> e.equals(x))).collect(Collectors.toList());

        System.out.println(result);
    }

    /**
     * a - b
     */
    @Test
    public void testWork2() {
        List<String> list1 = Lists.newArrayList("a", "b", "ab");
        List<String> list2 = Lists.newArrayList("a", "c", "ab");

        List<String> result =
            list1.stream().filter(x -> list2.stream().noneMatch(e -> e.equals(x))).collect(Collectors.toList());

        System.out.println(result);
    }
}


