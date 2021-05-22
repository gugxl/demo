package com.gugu.demo.jvm0522;

/**
 * @author Administrator
 * @Classname Animal
 * @Description TODO
 * @Date 2021/5/22 20:37
 */
public class Animal {

}
class Dog extends Animal{

}
class Lion extends Animal{

}

class Test0522_6{
    public void run(Animal animal){
        System.out.println("animal run");
    }
    public void run(Dog dog){
        System.out.println("dog run");
    }
    public void run(Lion lion){
        System.out.println("lion run");
    }
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal lion = new Lion();
        Test0522_6 test0522_6 = new Test0522_6();
        test0522_6.run(dog);
        test0522_6.run(lion);
    }
}