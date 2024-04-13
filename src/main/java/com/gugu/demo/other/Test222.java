package com.gugu.demo.other;

import java.lang.reflect.Field;

/**
 * @author Administrator
 * @Classname Test222
 * @Description TODO
 * @Date 2021/6/2 6:43
 */
public class Test222 {
    // jdk9之前反射可以访问任意类型的私有字段，但是模块化之后，
    // --add-opens java.base/java.lang=ALL-UNNAMED
    public static void main(String[] args) throws Exception{
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(null);
// array[129] is 1
        array[130] = array[129];
// Set 2 to be 1
        array[131] = array[129];
// Set 3 to be 1
        Integer a = 1;
        if(a == (Integer)1 && a == (Integer)2 && a == (Integer)3){
            System.out.println("Success");
        }
    }
}
