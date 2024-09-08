package com.gugu.demo.algorithm.leetcode;

public class Solution977 {

    public static void main(String[] args) {
        int[] ints = sortedSquares(new int[]{-4, -1, 0, 3, 10});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
    public static int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0 ;
        int right =  nums.length-1;
        for (int i = nums.length-1; i >= 0 ; i--) {
            if (nums[left]*nums[left] > nums[right]*nums[right]) {
                res[i] = nums[left]*nums[left];
                left++;
            }else {
                res[i] = nums[right]*nums[right];
                right--;
            }
        }
        return res;
    }
}
