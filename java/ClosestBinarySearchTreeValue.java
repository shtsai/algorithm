/*
    Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

    Note:
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target. 
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

// Solution 1: BST search
// Perform standard BST search, while keep track of the value that is closest to target.
// Time: O(logn) - logn levels in the tree
// Space: O(1)
// 10/10/2017

class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode p = root;
        int min = Integer.MAX_VALUE;
        double diff = Double.MAX_VALUE;
        while (p != null) {
            if (Math.abs(p.val - target) < diff) {
                min = p.val;
                diff = Math.abs(p.val - target);
            }      
            if (p.val > target) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return min;
    }
}