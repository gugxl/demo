package com.gugu.demo.algorithm;




public class Solution175 {
    public static void main(String[] args) {
        System.out.println(calculateDepth(null));
    }

    public static int calculateDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(calculateDepth(root.left, 1), calculateDepth(root.right, 1));
    }

    public static int calculateDepth(TreeNode cur, int js) {
        if (cur == null){
            return js;
        }
        return Math.max(calculateDepth(cur.left, js+1), calculateDepth(cur.right, js+1));
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
