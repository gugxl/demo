package com.gugu.demo.util.collections4;


import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @Classname ConcurrentHashMapDemo
 * @Description TODO
 * @Date 2021/11/28 13:55
 */
public class ConcurrentHashMapDemo {
    private Map<Integer, Integer> cache =  new ConcurrentHashMap<>(15);
    @Test
    public void test() {
        ConcurrentHashMapDemo concurrentHashMapDemo = new ConcurrentHashMapDemo();
        System.out.println(concurrentHashMapDemo.fibonaacci(80));
    }

    public int fibonaacci(Integer i){
        if(i==0||i ==1) {
            return i;
        }
        return cache.computeIfAbsent(i, key -> {
            System.out.println("fibonaacci : "+ key);
            return fibonaacci(i-1) + fibonaacci(i-2);
        });
    }
}
