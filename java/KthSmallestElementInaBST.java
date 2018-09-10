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

// Solution 2: binary search
// Time: O(logn)
// Space: O(1)
// 09/10/2018
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

// Solution 1: Inorder traversal
// Time: O(n)
// Space: O(n) - stack
// 09/09/2018
class Solution {
    int count;
    int res;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        inorder(root, k);
        return res;
    }
    
    private void inorder(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        inorder(root.left, target);
        count++;
        if (count == target) {
            res = root.val;
        }
        inorder(root.right, target);
    }
}