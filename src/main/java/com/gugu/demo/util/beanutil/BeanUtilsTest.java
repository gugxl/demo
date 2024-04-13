package com.gugu.demo.util.beanutil;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * https://mp.weixin.qq.com/s/ExZ0ZRwrZcvJj7dZklpouA
 */
public class BeanUtilsTest {
    @SneakyThrows
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("tom");
        person.setAge(21);
        // 注意要求 Person 是 public
        Person person1 =  (Person) BeanUtils.cloneBean(person);
        System.out.println(person1.getName()+">>"+person1.getAge());

        // 原理通过Java的反射机制来做的。
        //  2、 将一个Map对象转化为一个Bean
//  这个Map对象的key必须与Bean的属性相对应。
        Map map = new HashMap();
        map.put("name","tom");
        map.put("email","tom@");
        map.put("age","21");
        //将map转化为一个Person对象
        Person person2 = new Person();
        BeanUtils.populate(person2, map);
        System.out.println(person2);
//  通过上面的一行代码，此时person的属性就已经具有了上面所赋的值了。
        BeanUtils.populate(person,map);
//  将一个Bean转化为一个Map对象了，如下：
        Map map2 = BeanUtils.describe(person);
        System.out.println(map2);
    }

    @Data
    public static class Person {
        private String name;
        private String email;
        private int age;
    }
}


