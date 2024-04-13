package com.gugu.demo.algorithm;

public class Solution018 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("   "));
    }

    public static boolean isPalindrome(String s) {
        if (s.length() < 2)
            return true;
        int start = 0;
        s = s.toLowerCase();
        int end = s.length() - 1;
        while (start < end) {
            while (start < s.length() && !check(s.charAt(start))) {
                start++;
            }
            while (end >= 0 && !check(s.charAt(end))) {
                end--;
            }
            if (start >= s.length()) {
                return true;
            }

            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    private static boolean check(char c) {
        return (c <= 'z' && c >= 'a') || (c <= '9' && c >= '0');
    }
}
