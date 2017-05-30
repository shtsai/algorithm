/*
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example,
 * Given
 * 
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
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
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    public TreeNode helper(TreeNode root) {
        if (root == null) return root;
        TreeNode right = root.right;    // save the right subtree
        root.right = null;
        
        if (root.left != null) {
            root.right = helper(root.left); // flatten left subtree
            root.left = null;
        }
        if (right != null) {
            TreeNode p = root;
            while (p.right != null) p = p.right;
            p.right = helper(right);
        }

        return root;
    }
}