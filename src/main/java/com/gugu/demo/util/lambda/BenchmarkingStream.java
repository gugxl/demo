package com.gugu.demo.util.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BenchmarkingStream {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        // 准备数据
        for (int i = 0; i < 100_000; i++) {
            stringList.add(i+"");
        }
        String joinStr = stringList.stream().collect(Collectors.joining("`"));
        // 字符串匹配
        long startTime = System.currentTimeMillis();
        if (joinStr.startsWith("88888`") || joinStr.endsWith("88888`") || joinStr.contains("88888`")) {
            System.out.println("exist!");
        }
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        if (joinStr.contains("88888`")) {
            System.out.println("exist!");
        }
        System.out.println(System.currentTimeMillis() - startTime);

        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
