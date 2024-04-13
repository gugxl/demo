package com.gugu.demo.util.beanutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gugu
 * @Classname Account
 * @Description TODO
 * @Date 2022/7/24 9:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;
    private String name;
    private Double money;
}
