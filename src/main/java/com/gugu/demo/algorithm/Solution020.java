package com.gugu.demo.algorithm;

public class Solution020 {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length() * 2 - 1; i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                count++;
            }

        }
        return count;

    }
}
