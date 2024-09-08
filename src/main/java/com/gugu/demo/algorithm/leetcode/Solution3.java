package com.gugu.demo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bpfbhmipx"));

    }
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int index = map.get(c);
                map.clear();
                for (int j = index+1; j < i+1; j++) {
                    map.put(s.charAt(j), j);
                }
            }else {
                map.put(c, i);
                max = Math.max(max, map.size());
            }
            if (i == s.length() - 1) {
                max = Math.max(max, map.size());
            }
        }
        return max;
    }
}
