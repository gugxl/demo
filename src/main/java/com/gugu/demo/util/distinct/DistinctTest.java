package com.gugu.demo.util.distinct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname DistinctTest
 * @Description TODO
 * @Date 2021/11/1 23:45
 */
public class DistinctTest {
    private static Collector numbersList;

    public static void main(String[] args) {
        List<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3,5, 6, 6, 6, 3, 3, 4,  7, 8));
        System.out.println(linkedHashSetMethod(numbersList));
        System.out.println(streamMethod(numbersList));
        System.out.println(hashSetMethod(numbersList));
        System.out.println(containsMethod(numbersList));
        System.out.println(for2Method(numbersList));

    }
    // 1. LinkedHashSet  去重
    public static List<Integer> linkedHashSetMethod(Collection<Integer> numbersList){
        Set<Integer> integers = new LinkedHashSet<>(numbersList);
        ArrayList<Integer> integers1 = new ArrayList<>(integers);
        return integers1;
    }

    //2. stream
    public static List<Integer> streamMethod(Collection<Integer> numbersList){
        List<Integer> collect = numbersList.stream().distinct().collect(Collectors.toList());
        return collect;
    }

    //3. 利用 HashSet 不能添加重复数据的特性 由于 HashSet 不能保证添加顺序，所以只能作为判断条件保证顺序
    public static List<Integer> hashSetMethod(Collection<Integer> numbersList){
        List<Integer> result = new ArrayList<>(numbersList.size());
        Set<Integer> set = new HashSet<>(numbersList.size());
        for (Integer integer : numbersList) {
            if (set.add(integer)){
                result.add(integer);
            }
        }
        return result;
    }

    //4. 利用 List 的 contains 方法循环遍历, 重新排序, 只添加一次数据, 避免重复：
    public static List<Integer> containsMethod(Collection<Integer> numbersList) {
        List<Integer> result = new ArrayList<>(numbersList.size());
        for (Integer integer : numbersList) {
            if (!result.contains(integer)){
                result.add(integer);
            }
        }
        return result;
    }

    // 5. 双重 for 循环去重
    public static List<Integer> for2Method(List<Integer> numbersList) {
        for (int i = 0; i < numbersList.size(); i++) {
            for (int j = 0; j < numbersList.size(); j++) {
                if (i!=j && numbersList.get(i) == numbersList.get(j)){
                    numbersList.remove(numbersList.get(j));
                }
            }
        }
        return numbersList;
    }
}
