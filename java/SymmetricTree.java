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


// recursive solution
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
        return helper(left.left, right.right) && helper(left.right, right.left);    //recursively check the next level
        
    }
}