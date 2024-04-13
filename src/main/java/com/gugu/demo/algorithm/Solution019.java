package com.gugu.demo.algorithm;

public class Solution019 {
    public static void main(String[] args) {
        System.out.println(validPalindrome("abc"));
    }

    public static boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return isValidhw(start + 1, end, s) || isValidhw(start, end - 1, s);
            }
        }
        return true;
    }

    private static boolean isValidhw(int start, int end, String s) {
        while (start < end) {
            if (start < s.length() && end >= 0 && s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return true;
    }
}
