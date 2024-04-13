package com.gugu.demo.util.json.json.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gugu
 * @Classname Person
 * @Description TODO
 * @Date 2022/9/25 19:56
 */
@Data
public class Person {
    private String name;
    private FullName fullName;
    private int age;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> clothes;
    private List<Person> friends;

    public Person() {
    }

    public Person(String name, FullName fullName, int age, Date birthday, List<String> hobbies, Map<String, String> clothes, List<Person> friends) {
        this.name = name;
        this.fullName = fullName;
        this.age = age;
        this.birthday = birthday;
        this.hobbies = hobbies;
        this.clothes = clothes;
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", fullName=" + fullName +
                ", age=" + age +
                ", birthday=" + birthday +
                ", hobbies=" + hobbies +
                ", clothes=" + clothes +
                ", friends=" + friends +
                '}';
    }
}
