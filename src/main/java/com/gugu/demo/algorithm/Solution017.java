package com.gugu.demo.algorithm;

public class Solution017 {


    public static void main(String[] args) {
        System.out.println(calculate("AB"));

    }

    public static int calculate(String s) {
         int x = 1;
         int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                x = 2 * x + y;
            } else {
                y = 2 * y + x;
            }
        }
        return x + y;
    }
}
