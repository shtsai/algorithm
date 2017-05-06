/*
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
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

// reverse in-order traversal, from largest to smallest
// keep a running sum of all the nodes
public class Solution {
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        converter(root);
        return root;
    }
    
    public void converter(TreeNode root) {
        if (root == null) return;
        converter(root.right);
        sum += root.val;
        root.val = sum;
        converter(root.left);
    }
}