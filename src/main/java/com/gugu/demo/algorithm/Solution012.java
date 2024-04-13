package com.gugu.demo.algorithm;

import java.util.Arrays;

public class Solution012 {
    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{2, 1, -1}));
    }

    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }

        return -1;
    }
}
