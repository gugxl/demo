package com.gugu.demo.algorithm;

//https://leetcode.cn/problems/longest-palindromic-substring/
public class Solution5 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }

    public static String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            String tmp = "";

            // 以当前字符下一位为对称轴，
            if ((i + 1) < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                tmp = s.charAt(i) + tmp + s.charAt(i);
                while (j-- > 0 && (2 * i - j + 1) < s.length() && (s.charAt(j) == s.charAt(2 * i - j + 1))) {
                    tmp = s.charAt(j) + tmp + s.charAt(j);
                }
                if (tmp.length() > res.length()) {
                    res = tmp;
                }
            }

            // 以当前字符为对称轴
            tmp = s.charAt(i) + "";
            j = i;
            while (j-- > 0 && (2 * i - j) < s.length() && (s.charAt(j) == s.charAt(2 * i - j))) {
                tmp = s.charAt(j) + tmp + s.charAt(j);
            }
            if (tmp.length() > res.length()) {
                res = tmp;
            }
        }
        return res;
    }
}
