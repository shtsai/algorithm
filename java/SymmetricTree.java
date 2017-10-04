/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
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

// Solution 2: iterative solution
// Use a stack to store the nodes we need compare
// 10/04/2017

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        if (root.left != null) {
            if (root.right == null) return false;
            stack.push(root.left);
            stack.push(root.right);
        } else if (root.right != null) {
            return false;
        }
        
        while (!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if (right.val != left.val) return false;
            if (left.left != null) {
                if (right.right == null) return false;
                stack.push(left.left);
                stack.push(right.right);
            } else if (right.right != null) return false;
            
            if (left.right != null) {
                if (right.left == null) return false;
                stack.push(left.right);
                stack.push(right.left);
            } else if (right.left != null) return false;
        }
        return true;
    }
}

// Solution 1: recursive solution
// Check a few cases:
//      1. if root is null, return true
//      2. if left and right don't have the same value, or at least one of them is null
//         return false
//      3. if left and right have the same value, recursively check their children
// reference:https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java
// 10/04/2017
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {  // at least one of the node is null
            return left == right;             // if they are both null, return true, otherwise return false
        }
        
        if (left.val != right.val) return false;
        // key step
        return helper(left.left, right.right) && helper(left.right, right.left);    //recursively check the next level
        
    }
}