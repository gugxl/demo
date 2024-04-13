package com.gugu.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Solution0383 {
    public static void main(String[] args) {
        System.out.println(canConstruct("dehifb", "hhjdgjbhahaagihhhhhajjibjffhijehda"));

    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            Integer count = countMap.getOrDefault(magazine.charAt(i), 0);
            countMap.put(magazine.charAt(i), ++count);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            Integer count = countMap.getOrDefault(ransomNote.charAt(i), 0);
            --count;
            if (count < 0) {
                return false;
            }
            countMap.put(ransomNote.charAt(i), count);
        }

        return true;
    }
}
