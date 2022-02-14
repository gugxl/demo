package com.gugu.demo.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Administrator
 * @Classname ListTest
 * @Description TODO
 * @Date 2022/2/14 21:17
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        //对原有的list进行包装，相等于原有List的一个视图，快照，不够安全
        List<String> readList  = Collections.unmodifiableList(list);
        readList.add("d");//对这个视图增操作，错误，抛出java.lang.UnsupportedOperationException（不被支持的异常）。
        list.add("e");//正确，改变原有List，视图也一起改变，没有达到真正的目的，所以不够安全。

        // guava对只读设置 安全可靠，并且相对简单
        ImmutableList<Object> immutableList = ImmutableList.builder().add("a", "b", "c").build();
        ImmutableList.of("red","green","black","white","grey"); // 方式2
        ImmutableList.copyOf(new String[]{"red","green","black","white","grey"}); // 方式3
        immutableList.add("d");//java.lang.UnsupportedOperationException

        ArrayList<String> arrayList = Lists.newArrayList();
        HashSet<Object> hashSet = Sets.newHashSet();
        HashMap<Object, Object> hashMap = Maps.newHashMap();

    }
}
