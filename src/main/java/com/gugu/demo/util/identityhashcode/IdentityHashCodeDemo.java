package com.gugu.demo.util.identityhashcode;

/**
 * @author gugu
 * @Classname IdentityHashCodeDemo
 * @Description hashCode方法可以被重写并返回重写后的值，identityHashCode会返回对象的hash值而不管对象是否重写了hashCode方法。
 * @Date 2022/6/12 11:15
 */
public class IdentityHashCodeDemo {
    public static void main(String[] args) {
        String str = new String("gugu");
        String str2 = new String("gugu");
        System.out.println("str.hashCode:" + str.hashCode());
        System.out.println("str2.hashCode:" + str2.hashCode());
        // str1和str2的hashCode是相同的，是因为String类重写了hashCode方法

        System.out.println("str.identityHashCode:" + System.identityHashCode(str));
        System.out.println("str2.identityHashCode:" + System.identityHashCode(str2));
        // str1和str2的identityHashCode不一样，虽然String重写了hashCode方法，identityHashCode永远返回根据对象物理内存地址产生的hash值，所以每个String对象的物理地址不一样，identityHashCode也会不一样。

        User user = new User(1L,"gugu");
        System.out.println("user.hashCode:" + user.hashCode());
        System.out.println("user.identityHashCode:" + System.identityHashCode(user));
        // User对象没重写hashCode方法，所以hashCode和identityHashCode返回的值一样。
    }
}

class User{
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
