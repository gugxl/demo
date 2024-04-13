package com.gugu.demo.util.proxy.staticproxy;

/**
 * @author gugu
 * @Classname CarProxy
 * @Description TODO
 * @Date 2022/9/3 17:47
 */
public class CarProxy implements Car {
    private CarImpl ci;

    public CarProxy(CarImpl ci) {
        this.ci = ci;
    }

    @Override
    public void revCar() {
        System.out.println("该客户是否有足够的钱预订车");
        ci.revCar();
        System.out.println("该客户预订完成");
    }

    @Override
    public void buyCar() {
        System.out.println("该客户是否是vip客户");
        ci.buyCar();
        System.out.println("该客户买车完成");
    }
}
