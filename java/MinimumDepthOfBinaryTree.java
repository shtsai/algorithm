/*
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// recursive solution
public class Solution {
    public int minDepth(TreeNode root) {
        return helper(root);
    }
    
    public int helper(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null) return helper(node.right) + 1;
        if (node.right == null) return helper(node.left) + 1;
    
        return Math.min(helper(node.left) + 1, helper(node.right) + 1);
    }
}