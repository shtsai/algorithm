/*
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
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

// Solution 1: DFS, recursively check for every node
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumThrough(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    // this helper function checks for path thats goes through the input node
    public int pathSumThrough(TreeNode root, int sum) {
        if (root == null) return 0;
        int result = 0;
        if (root.val == sum) result++;
        return result + pathSumThrough(root.left, sum - root.val) + pathSumThrough(root.right, sum - root.val);
    }
}