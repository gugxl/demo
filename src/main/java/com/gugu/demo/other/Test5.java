package com.gugu.demo.other;

/**
 * @Description 抽象产品角色
 * @auther Administrator
 */
interface Moveable {
    void run();
}

/**
 * @Description 具体产品角色
 * @auther Administrator
 */
class Plane implements Moveable {
    @Override
    public void run() {
        System.out.println("plane....");
    }
}

class Broom implements Moveable {
    @Override
    public void run() {
        System.out.println("broom.....");
    }
}



/**
 * @Description 具体工厂
 * @auther Administrator
 */
class PlaneFactory extends VehicleFactory {
    @Override
    public Moveable create() {
        return new Plane();
    }
}

class BroomFactory extends VehicleFactory {
    @Override
    public Moveable create() {
        return new Broom();
    }
}

public class Test5 {
    public static void main(String[] args) {
        VehicleFactory factory = new BroomFactory();
        Moveable m = factory.create();
        m.run();
    }
}