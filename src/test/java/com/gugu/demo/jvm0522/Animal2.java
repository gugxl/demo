package com.gugu.demo.jvm0522;

/**
 * @author Administrator
 * @Classname Animal2
 * @Description TODO
 * @Date 2021/5/22 22:58
 */
public abstract class Animal2 {
    public abstract void run();
}
class Dog2 extends Animal2{
    @Override
    public void run() {
        System.out.println("Dog2 run");
    }
}
class Lion2 extends Animal2{
    @Override
    public void run() {
        System.out.println("Lion2 run");
    }
}

class Test4{
    public static void main(String[] args) {
        Animal2 dog = new Dog2();
        Animal2 lion = new Lion2();;
        dog.run();
        lion.run();
    }
}