package com.gugu.demo.algorithm;

/**
 * https://leetcode.cn/problems/same-tree/
 */
public class Solution4 {
    public static void main(String[] args) {
        TreeNode2 p = new TreeNode2();
        TreeNode2 q = new TreeNode2();
        System.out.println(new Solution4().isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode2 p, TreeNode2 q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }
}

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;

    TreeNode2() {
    }

    TreeNode2(int val) {
        this.val = val;
    }

    TreeNode2(int val, TreeNode2 left, TreeNode2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
