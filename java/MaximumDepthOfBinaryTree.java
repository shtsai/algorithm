/**
 * Given a binary tree, find its maximum depth.
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
public class Solution {
    public int maxDepth(TreeNode root) {
        // use recursion to break down the problem

        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
            
            /* could be replaced by Math.max
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            if (left > right) { return 1 + left; }
            else { return 1 + right; }
            */
        }
    }
}