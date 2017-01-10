/*
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree 
 * in which the depth of the two subtrees of every node never differ by more than 1.
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

// DFS (bottom up)
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return depth(root) != -1;
    }
    
    public int depth(TreeNode root) {
        if (root == null) return 0;
        
        int left = depth(root.left);
        if (left == -1) return -1;
        
        int right = depth(root.right);
        if (right == -1) return -1;
        
        if (Math.abs(left - right) > 1) return -1;
        
        return Math.max(left, right) + 1;
    }
}

/*
// brute force (top down approach)
// check if every node is balanced
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = height(root.left);
        int right = height(root.right);
        return Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    // a helper function that determines the height of a tree
    public int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
*/