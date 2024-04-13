package com.gugu.demo.util.proxy.staticproxy;

/**
 * @author gugu
 * @Classname CarImpl
 * @Description 委托类
 * @Date 2022/9/3 17:46
 */
public class CarImpl implements Car {
    @Override
    public void revCar() {
        System.out.println("我要去预订一辆车咯");
    }

    @Override
    public void buyCar() {
        System.out.println("我要买车咯");
    }
}
