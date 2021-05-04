package com.gugu.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @Classname UserDO
 * @Description TODO
 * @Date 2021/3/31 22:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private Long id;
    private String name;
}
