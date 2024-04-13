package com.gugu.demo.util.json.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Administrator
 * @Classname User
 * @Description TODO
 * @Date 2021/9/4 9:01
 */
@Data
public class User {
    @SerializedName(value = "USER_NAME")
    @Expose
    private String username;
    private int age;
    @Expose
    private int gender;
    @Expose(deserialize = false)
    private String genderDesc;

    public User() {
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
