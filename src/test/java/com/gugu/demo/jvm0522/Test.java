package com.gugu.demo.jvm0522;

/**
 * @author Administrator
 * @Classname Test
 * @Description TODO
 * @Date 2021/5/22 20:10
 */
public class Test {
    static {
        i = 20;//编译可以正常通过
//        System.out.println(i);//提示illegal forward reference错误
    }
    static int i = 10;
}
