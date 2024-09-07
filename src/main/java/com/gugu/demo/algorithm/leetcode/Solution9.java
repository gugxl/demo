package com.gugu.demo.algorithm.leetcode;

public class Solution9 {
    public static void main(String[] args) {

    }
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        String s = String.valueOf(x);
        if (s.equals(new StringBuilder(s).reverse().toString())){
            return true;
        }
        return false;
    }
}
