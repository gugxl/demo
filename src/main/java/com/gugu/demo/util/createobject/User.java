package com.gugu.demo.util.createobject;

import java.io.Serializable;

public class User implements Serializable, Cloneable {

    private static final long serialVersionUID = -8840853971861469954L;

    public User() {
        System.out.println("调用空的构造方法");
    }

    public User(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }

    private String userName;
    // transient 关键词修饰的属性不会序列化
    private transient String address;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}