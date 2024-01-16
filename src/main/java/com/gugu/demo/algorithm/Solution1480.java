package com.gugu.demo.algorithm;

public class Solution1480 {
    public static void main(String[] args) {

    }
    public int[] runningSum(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] + nums[i];
        }
        return res;
    }
}
