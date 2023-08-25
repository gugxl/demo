package com.gugu.demo.other;
/**
 * @Description 蔬菜类
 * @auther Administrator
 */

public class Vegetable extends Food2 {

    private Food2 basic_food;

    public Vegetable(Food2 basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make() + "+蔬菜";
    }

}