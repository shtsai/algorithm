/*
 * Given a binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * 
 * For example:
 * Given the below binary tree,
 * 
 *        1
 *       / \
 *      2   3
 * Return 6.
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
// Recursive solution, top down approach
// Each call to helper function will compute the max path sum
// that goes through the current node, and update max accordingly.
// Node that node value can be negative. If that is the case,
// we don't include negative values.
// After updating max, the function returns a sided path that goes
// through the current node, so that its parent node can do its calculation.
//
// Time: O(n) - call helper function on each node exactly once
// Space: O(1)

class Solution {
    int max;
    
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSumThrough(root);
        return max;
    }
    
    /* a helper function that finds that maxPathSum that goes through this node */
    private int maxPathSumThrough(TreeNode node) {
        if (node == null) return 0;
        // if left or right is less than 0, there is no reason to add it to the max path. We can set it to 0, meaning it is not included in the path
        int left = Math.max(0, maxPathSumThrough(node.left));  
        int right = Math.max(0, maxPathSumThrough(node.right));
        
        // check if path from left->node->right is the max path
        max = Math.max(max, left + right + node.val);
        
        // return the maximum single sided path
        return Math.max(left, right) + node.val;
    }
}
