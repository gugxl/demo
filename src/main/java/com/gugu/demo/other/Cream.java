package com.gugu.demo.other;

/**
 * @Description 奶油类
 * @auther Administrator
 */

public class Cream extends Food2 {

    private Food2 basic_food;

    public Cream(Food2 basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make() + "+奶油";
    }
}