package com.gugu.demo.util.classload;

/**
 * @author Administrator
 * @Classname ClassLoaderTest
 * @Description TODO
 * @Date 2021/10/17 11:15
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader c1 = ClassLoaderTest.class.getClassLoader();
        System.out.println(c1);
        ClassLoader parent = c1.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
    }
}
