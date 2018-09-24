/*
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
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

// Solution 1: recursive approach
// by the property of preorder traversal, the first element is the root
// find that element in inorder array, then divide the original problem to two halves and solve recursively
// Time: O(n)
// Space: O(n) - call stack
// 09/23/2018
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        return buildHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode buildHelper(int[] preorder, int[] inorder, int pIndex, int iStart, int iEnd) {
        if (iStart > iEnd) {
            return null;
        } 
        TreeNode root = new TreeNode(preorder[pIndex]);
        int mid = iStart;
        while (inorder[mid] != root.val) {
            mid++;
        }
        root.left = buildHelper(preorder, inorder, pIndex + 1, iStart, mid - 1);
        root.right = buildHelper(preorder, inorder, pIndex + (mid - iStart) + 1, mid + 1, iEnd);
        return root;
    }
}