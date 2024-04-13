package com.gugu.demo.util.json.json.bean;

import lombok.Data;

/**
 * @author gugu
 * @Classname FullName
 * @Description TODO
 * @Date 2022/9/25 19:57
 */
@Data
public class FullName {
    private String firstName;
    private String middleName;
    private String lastName;

    public FullName() {
    }

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
