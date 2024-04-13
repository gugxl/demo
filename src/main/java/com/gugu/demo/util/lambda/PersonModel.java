package com.gugu.demo.util.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @Classname PersonModel
 * @Description TODO
 * @Date 2021/8/18 23:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModel implements Comparable {
    private String name;
    private Integer age;
    private String sex;

    @Override
    public int compareTo(Object o) {
        PersonModel p = (PersonModel) o;
        return p.getAge().compareTo(age);
    }


}
