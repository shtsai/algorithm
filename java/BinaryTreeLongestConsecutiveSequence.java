/*
    Given a binary tree, find the length of the longest consecutive sequence path.

    The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

    Example 1:

    Input:

       1
        \
         3
        / \
       2   4
            \
             5

    Output: 3

    Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
    Example 2:

    Input:

       2
        \
         3
        / 
       2    
      / 
     1

    Output: 2 

    Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
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

// Solution 1: Buttom-up Recursion
// Maintain a global max.
// Use a helper function to recursively find longest consecutive path.
//
// Time: O(n)
// Space: O(n) - stack for skewed tree
// 09/05/2018
class Solution {
    int max;
    public int longestConsecutive(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int res = 1;
        if (root.left != null && root.left.val == root.val + 1) {
            res = Math.max(res, left + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            res = Math.max(res, right + 1);
        }
        max = Math.max(max, res);
        return res;
    }
}