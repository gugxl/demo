package com.gugu.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution0406 {
    public static void main(String[] args) {
        int[][] people = new int[6][];
        people[0] = new int[]{7,0};
        people[1] = new int[]{4,4};
        people[2] = new int[]{7,1};
        people[3] = new int[]{5,0};
        people[4] = new int[]{6,1};
        people[5] = new int[]{5,2};

        System.out.println(reconstructQueue(people));
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });

        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
