package com.gugu.demo.algorithm;

public class Solution0142 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(4)));
        System.out.println(trainningPlan(l1, l2));

    }

    public static ListNode trainningPlan(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = trainningPlan(l1.next, l2);
            return l1;
        } else {
            l2.next = trainningPlan(l1, l2.next);
            return l2;
        }
    }


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

}
