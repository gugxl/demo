package com.gugu.demo.algorithm.leetcode;

public class Solution2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(8);
        ListNode l2 = new ListNode(9);
        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode head = result;
        int tmp = 0;

        while (l1 != null || l2 != null || tmp != 0) {
            int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + tmp;
            tmp = val / 10;
            result.val = val % 10;
            l1 = l1 != null ? l1.next: null;
            l2 = l2 != null ? l2.next : null;
            if (l1 != null || l2 != null || tmp != 0){
                result.next = new ListNode();
            }
            result = result.next;
        }
        return head;
    }




    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
