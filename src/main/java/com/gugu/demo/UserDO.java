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
public class UserDO {
    private Long id;
    private String name;

    public UserDO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
