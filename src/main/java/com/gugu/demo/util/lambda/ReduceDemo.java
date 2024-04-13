package com.gugu.demo.util.lambda;

import java.util.stream.Stream;

/**
 * @author Administrator
 * @Classname ReduceDemo
 * @Description TODO
 * @Date 2021/8/19 0:11
 */
public class ReduceDemo {
    public static void reduceTest() {
        // 累加，初始值10
        Integer reduce = Stream.of(1, 2, 3, 4).reduce(10, (count, item) -> {
            System.out.println("count:" + count);
            System.out.println("item:" + item);
            return count + item;
        });
        System.out.println(reduce);

        Integer reduce1 = Stream.of(1, 2, 3, 4).reduce(0, (x, y) -> x + y);
        System.out.println(reduce1);
        String reduce2 = Stream.of("1", "2", "3", "4").reduce("0", (x, y) -> (x + "," + y));
        System.out.println(reduce2);
    }

    public static void main(String[] args) {
        reduceTest();
    }
}
