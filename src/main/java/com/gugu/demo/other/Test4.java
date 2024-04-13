package com.gugu.demo.other;

// 抽象产品类
interface Car2 {
    public void run();

    public void stop();
}

// 具体实现类
class Benz implements Car2 {
    @Override
    public void run() {
        System.out.println("Benz开始启动了。。。。。");
    }

    @Override
    public void stop() {
        System.out.println("Benz停车了。。。。。");
    }
}

class Ford implements Car2 {
    @Override
    public void run() {
        System.out.println("Ford开始启动了。。。");
    }

    @Override
    public void stop() {
        System.out.println("Ford停车了。。。。");
    }
}

// 工厂类
class Factory {
    public static Car2 getCarInstance(String type) {
        Car2 c = null;
        if ("Benz".equals(type)) {
            c = new Benz();
        }
        if ("Ford".equals(type)) {
            c = new Ford();
        }
        return c;
    }
}

/**
 * @author Administrator
 */
public class Test4 {

    public static void main(String[] args) {
        Car2 c = Factory.getCarInstance("Benz");
        if (c != null) {
            c.run();
            c.stop();
        } else {
            System.out.println("造不了这种汽车。。。");
        }

    }

}