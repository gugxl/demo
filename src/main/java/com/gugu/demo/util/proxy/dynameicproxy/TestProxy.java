package com.gugu.demo.util.proxy.dynameicproxy;

/**
 * @author gugu
 * @Classname TestProxy
 * @Description TODO
 * @Date 2022/9/3 18:10
 */
public class TestProxy {
    public static void main(String[] args) {
        CarProxy carProxy = new CarProxy();
        // 传入一个实现了该接口的实例就行
        Car car = (Car)carProxy.bind(new CarImp1());
        Car car2 = (Car)carProxy.bind(new CarImp2());
        car.buyCar();
        car2.buyCar();
    }
}
