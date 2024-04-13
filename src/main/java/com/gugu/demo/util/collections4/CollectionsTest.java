package com.gugu.demo.util.collections4;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionsTest {
    public static void main(String[] args) {
        // LinkedMapTest();
        // TreeBidiMapTest();
        retainAllTest();
    }
    /**
     * 得到两个集合中相同的元素
     */
    private static void retainAllTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("5");
        Collection<String> c = CollectionUtils.retainAll(list1, list2);
        System.out.println(c);
    }

    /**
     * 通过key得到value
     * 通过value得到key
     * 将map里的key和value对调
     * 注意：不管key和value那个重复都会覆盖
     */
    private static void TreeBidiMapTest() {
        BidiMap bidi = new TreeBidiMap();
        bidi.put("SIX", "6");
        System.out.println(bidi.get("SIX"));
        System.out.println(bidi.getKey("6"));

        BidiMap inverse = bidi.inverseBidiMap();  // returns a map with keys and values swapped
        System.out.println(inverse);
    }
    /**
     * 得到集合里按顺序存放的key之后的某一Key
     */
    private static void LinkedMapTest() {
        OrderedMap map = new LinkedMap();
        map.put("FIVE", "5");
        map.put("SIX", "6");
        map.put("SEVEN", "7");
        System.out.println(map.firstKey());// returns "FIVE"
        System.out.println(map.nextKey("FIVE"));// returns "SIX"
        System.out.println(map.nextKey("SIX"));// returns "SEVEN"
    }


}
