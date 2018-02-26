/*
    Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

    Example 1:
    Input: 
        1
       / \
      0   2

      L = 1
      R = 2

    Output: 
        1
          \
           2
    Example 2:
    Input: 
        3
       / \
      0   4
       \
        2
       /
      1

      L = 1
      R = 3

    Output: 
          3
         / 
       2   
      /
     1
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int x) { val = x; }
 * }
 */

// Solution 1: Recursive
// Time: O(n)
// Space: O(n) - worse case, linear tree
// 02/26/2018

public class Solution {
    public TreeNode TrimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        } else if (root.val < L) {
            return TrimBST(root.right, L, R);
        } else if (root.val > R) {
            return TrimBST(root.left, L, R);
        } else {
            root.left = TrimBST(root.left, L, R);
            root.right = TrimBST(root.right, L, R);
            return root;
        }
    }
}