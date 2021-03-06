package com.gugu.demo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @Classname Demo52
 * @Description TODO
 * @Date 2021/12/1 23:14
 */
public class Demo2 {



    // 结果
    List<Long> allId = new ArrayList<>();

    public void getAllId(Department department, String target){
        if (Objects.equals(target, department.getName())) {
            allId.add(department.getId());
        }
        List<Department> children = department.getChildren();
        if (null != children){
            for (int i = 0; i < children.size(); i++) {
                // 递归
                getAllId(children.get(i), target);
            }
        }
    }

    @Data
    class Department{
        private Long id;
        private String name;
        private List<Department> children;
    }
}
