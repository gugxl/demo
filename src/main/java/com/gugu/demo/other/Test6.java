package com.gugu.demo.other;

/**
 * @Description 抽象工厂类
 * @auther Administrator
 */
abstract class AbstractFactory {
    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}

interface Vehicle {
    void run();
}

interface Weapon {
    void shoot();
}

interface Food {
    void printName();
}

class Apple implements Food {
    @Override
    public void printName() {
        System.out.println("做饭");
    }
}

class Car implements Vehicle {
    @Override
    public void run() {
        System.out.println("行驶");
    }
}

class AK47 implements Weapon {
    @Override
    public void shoot() {
        System.out.println("开火");
    }
}

//具体工厂类，其中Food,Vehicle，Weapon是抽象类，
class DefaultFactory extends AbstractFactory {
    @Override
    public Food createFood() {
        return new Apple();
    }

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }

    @Override
    public Weapon createWeapon() {
        return new AK47();
    }
}

//测试类
public class Test6 {
    public static void main(String[] args) {
        AbstractFactory f = new DefaultFactory();
        Vehicle v = f.createVehicle();
        v.run();
        Weapon w = f.createWeapon();
        w.shoot();
        Food a = f.createFood();
        a.printName();
    }
}