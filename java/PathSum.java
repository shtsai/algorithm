/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
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

// use recursion to break the problem into smaller pieces
// version 2:
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;
        if (hasPathSum(root.left, sum - root.val)) return true;
        if (hasPathSum(root.right, sum - root.val)) return true;
        return false;
    }
}

// version 1:
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        // if a leaf node has a value that equals to the sum, return true
        // Note: because the problem is about path sum, need to test if the node is a leaf
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        
        // recursive call
        int newSum = sum - root.val;
        return hasPathSum(root.left,newSum) || hasPathSum(root.right, newSum);
    }
}