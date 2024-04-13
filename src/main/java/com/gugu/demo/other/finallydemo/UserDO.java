package com.gugu.demo.other.finallydemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Administrator
 * @Classname UserDO
 * @Description TODO
 * @Date 2021/3/31 22:10
 */
@Data
@ToString
public class UserDO {
    private Long id;
    private String name;

    public UserDO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
