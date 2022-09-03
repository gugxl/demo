package com.gugu.demo.proxy.cglibproxy;

/**
 * @author gugu
 * @Classname Test
 * @Description TODO
 * @Date 2022/9/3 18:19
 */
public class Test {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Train t = (Train)proxy.getProxy(Train.class);
        t.move();

    }
}
