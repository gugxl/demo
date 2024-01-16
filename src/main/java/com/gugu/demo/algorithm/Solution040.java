package com.gugu.demo.algorithm;

import java.util.Arrays;

public class Solution040 {
    public static void main(String[] args) {
        System.out.println(maxmiumScore(new int[]{1,3,4,5}, 4));

    }

    public static int maxmiumScore(int[] cards, int cnt) {
        int sum = 0;
        if (cards.length < 2) {
            return cards[0] % 2 == 0 ? cards[0] : 0;
        }

        Arrays.sort(cards);
        int i = 0;
        for (; i < cnt; i++) {
            sum += cards[cards.length - i - 1];
        }
        if (sum % 2 == 0)
            return sum;

        if (cards.length == cnt){
            return 0;
        }

        // 这个时候没结束就说明card里面是奇数，就要从 cards后面找到最小的奇数与card前面最大的偶数互换

        // 丢奇数 + 偶数
        int j = cards.length - i - 1;
        while (cards[j] % 2 == 0) {
            j++;
        }
        int k = cards.length - i - 1;
        while (k >= 0 && cards[k] % 2 != 0) {
            k--;
        }

        // 丢偶数 + 奇数
        int m = cards.length - i - 1;
        while (m < cards.length && cards[m] % 2 != 0) {
            m++;
        }
        int n = cards.length - i - 1;
        while (n >= 0 && cards[n] % 2 == 0) {
            n--;
        }

        if ((k < 0) && (m >= cards.length || n < 0))
            return 0;
        else {
            if (k < 0) {
                return sum - cards[m] + cards[n];
            }
            if (m >= cards.length || n < 0) {
                return sum - cards[j] + cards[k];
            }

            return Math.max(sum - cards[j] + cards[k], sum - cards[m] + cards[n]);
        }


    }
}
