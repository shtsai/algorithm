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

// Solution 2: In place
// Find predecessor of the root node.
// Point predecessor to the right subtree, and continue.
// Time: O(n)
// Space: O(1)
// 09/24/2018
class Solution {
    public void flatten(TreeNode root) {
        TreeNode p = root;
        while (p != null) {
            if (p.left != null) {
                // find predecessor of root
                TreeNode predecessor = p.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = p.right;
                p.right = p.left;
                p.left = null;
            }
            p = p.right;
        }
    }
}

// Solution 1: Recursion
// Observe that the flatten list is in pre-order traversal order.
// We can flatten the left subtree and right subtree first,
// then append left the root, and then append right.
// Time: O(n) - visit each node at most once
// Space: O(n) - call stack
// version 2:
// 11/15/2017
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        flatten(left);
        flatten(right);
        root.right = left;
        root.left = null;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}

// version 1:
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