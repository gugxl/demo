package com.gugu.demo.other;

/**
 * @Description 面包类
 * @auther Administrator
 */

public class Bread extends Food2 {

    private Food2 basic_food;

    public Bread(Food2 basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make() + "+面包";
    }
}
