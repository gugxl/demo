package com.gugu.demo.algorithm.leetcode;

public class Solution7 {
    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        int flag = 1;
        if (x < 0) {
            flag = -1;
            x = Math.abs(x);
        }
        int res = 0;
        int last = 0;
        while (x != 0) {
            last = res;
            res = res * 10 + x % 10;

            if (last != res / 10) {
                return 0;
            }
            x /= 10;
        }
        return flag * res;
    }
}
