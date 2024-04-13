package com.gugu.demo.algorithm;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(new Solution1().addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sup = 0;
        char[] aa = a.toCharArray();
        char[] ba = b.toCharArray();

        while (i >= 0 && j >= 0) {
            int tmp = aa[i--]-48 + ba[j--]-48 + sup;
            if (tmp > 1) {
                sup = 1;
                tmp = tmp - 2;
            } else {
                sup = 0;
            }
            res.append(tmp);
        }

        while (i >= 0) {
            int tmp = aa[i--] -48 + sup;
            if (tmp > 1) {
                sup = 1;
                tmp = tmp - 2;
            } else {
                sup = 0;
            }
            res.append(tmp);
        }

        while (j >= 0) {
            int tmp = ba[j--] -48 + sup;
            if (tmp > 1) {
                sup = 1;
                tmp = tmp - 2;
            } else {
                sup = 0;
            }
            res.append(tmp);
        }

        if(sup > 0){
            res.append(sup);
        }

        return res.reverse().toString();
    }
}
