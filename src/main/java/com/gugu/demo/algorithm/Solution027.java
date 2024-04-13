package com.gugu.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Solution027 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                        new ListNode(2,
                            new ListNode(1)))));
        System.out.println(isPalindrome(head));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        List<Integer> data = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            data.add(tmp.val);
            tmp = tmp.next;
        }

        for (int i = 0, j = data.size() - 1; i < (data.size() + 1) / 2; i++, j--) {
            if (data.get(i) != data.get(j)) {
                return false;
            }
        }
        return true;
    }
}
