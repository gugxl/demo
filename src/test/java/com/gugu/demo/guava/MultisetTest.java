package com.gugu.demo.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;

/**
 * @author Administrator
 * @Classname MultisetTest
 * @Description TODO
 * @Date 2022/2/14 21:35
 */
public class MultisetTest {
    public static void main(String[] args) {
        HashMultiset<String> multiset = HashMultiset.create();
        String sentences = "my name is java I love java very much";
        //Splitter 是guava提供的一个切割字符串的工具类
        Iterable<String> words = Splitter.onPattern("[^a-zA-Z]{1,}").omitEmptyStrings().trimResults().split(sentences);
        for (String word : words) {
            multiset.add(word);
        }
        for (String s : multiset.elementSet()) {
            System.out.println(s + ":" + multiset.count(s));
        }
    }
}
