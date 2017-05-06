/*
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
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

// binary search
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // count the number of left children
        int left = count(root.left);
        // base case
        if (k == left + 1) return root.val;
        
        if (k <= left) {  // continue searching on the left subtree
            return kthSmallest(root.left, k);
        } else {  // continue searching on the right subtree
            return kthSmallest(root.right, k-left-1);
        }
    }
    
    // a helper function to count the number of nodes rooted on the root node
    public int count(TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}