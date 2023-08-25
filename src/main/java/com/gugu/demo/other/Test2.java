package com.gugu.demo.other;

public class Test2 {
    public static void main(String[] args) {
        Food2 food = new Bread(new Vegetable(new Cream(new Food2("香肠"))));
        System.out.println(food.make());
    }
}