/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree 
 *          1
 *         / \
 *        2   3
 *       / \     
 *      4   5    
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
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
// use a global variable to maintain the max value for all nodes
public class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }
    
    // This is a helper function that calculates the depth of a given node
    public int depth (TreeNode root) {
        if (root == null) return 0;
        
        int left = depth(root.left);
        int right = depth(root.right);
        
        // keep track of the max yet
        max = Math.max(max, left+right);
        
        return 1 + Math.max(left, right);
    }
}


// Solution 2:
public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        int middle = depth(root.left) + depth(root.right);
        return Math.max(middle, Math.max(left, right));
    }
    
    public int depth (TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}