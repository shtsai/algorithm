/*
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
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

// Solution 1:
// in-order traversal
public class Solution {
    int minDiff = Integer.MAX_VALUE;
    Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return minDiff;
        
        minDiff = Math.min(getMinimumDifference(root.left), minDiff);
        
        if (prev != null) {
            minDiff = Math.min(minDiff, Math.abs(prev - root.val));
        }
        prev = root.val;
        
        minDiff = Math.min(getMinimumDifference(root.right), minDiff);
        
        return minDiff;
    }
}


// Solution 2:
public class Solution {
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        
        int minDiff = Integer.MAX_VALUE;
        
        // compare root with the max of left subtree
        if (root.left != null) {
            TreeNode leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
            minDiff = Math.min(minDiff, Math.abs(leftMax.val-root.val));
        }
        
        // compare root with the min of right subtree
        if (root.right != null) {
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
            minDiff = Math.min(minDiff, Math.abs(rightMin.val - root.val));
        }
        
        // recursively check left child and right child
        minDiff = Math.min(minDiff, getMinimumDifference(root.left));
        minDiff = Math.min(minDiff, getMinimumDifference(root.right));
        
        return minDiff;
    }
}