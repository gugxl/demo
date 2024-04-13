package com.gugu.demo.util.proxy.staticproxy;

/**
 * @author gugu
 * @Classname TestProxy
 * @Description TODO
 * @Date 2022/9/3 17:50
 */
public class TestProxy {
    public static void main(String[] args) {
        CarImpl ci = new CarImpl();
        CarProxy cp = new CarProxy(ci);
        cp.revCar();
        cp.buyCar();
    }
}
