/*
 * Find the sum of all left leaves in a given binary tree.
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
// use an additional parameter to see if the node is a left child
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }
    
    public int helper(TreeNode root, boolean left) {
        if (root == null) return 0;

        if (root.left == null && root.right == null && left) { // if the node is a left leaf
            return root.val;
        }
        
        return helper(root.left, true) + helper(root.right, false);
    }
}