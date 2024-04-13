package com.gugu.demo.algorithm;

// https://leetcode.cn/problems/single-number/?envType=study-plan-v2&envId=top-interview-150
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().singleNumber(new int[]{2, 2, 1}));
    }

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }
}
