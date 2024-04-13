package com.gugu.demo.util.randomaccess;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

/**
 * @author gugu
 * @Classname RandomAccessTest
 * @Description TODO
 * @Date 2022/9/7 23:04
 */
@Slf4j
public class RandomAccessTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        arrayList.add("b");
        long start1 = System.currentTimeMillis();
        iteratorList(arrayList);
        long end1 = System.currentTimeMillis();


        List<String> linkedList = new LinkedList<>();
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        linkedList.add("d");
        long start2 = System.currentTimeMillis();
        iteratorList(linkedList);
        long end2 = System.currentTimeMillis();

        System.out.println(end1 - start1);
        System.out.println(end2 - start2);
    }

    public static void traverse(List list) {
        if (list instanceof RandomAccess){
            System.out.println("实现了RandomAccess接口，不使用迭代器");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("没实现RandomAccess接口，使用迭代器");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }
    public static void forList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
    public static void iteratorList(List list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
