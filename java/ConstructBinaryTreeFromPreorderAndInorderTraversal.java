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
// Space: O(logn) - stack

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        return builder(preorder, inorder, 0, inorder.length-1, 0);
    }
    
    // inStart is the start index of the tree in the inorder array
    // inEnd is the end index of the tree in the inorder array
    // preStart is the index of the root of the current subtree
    private TreeNode builder(int[] preorder, int[] inorder,int inStart, int inEnd, int preStart) {
        if (inStart > inEnd || inStart > preorder.length || inEnd > preorder.length) {
            return null;
        }
        int rootIndex = inStart;                        // preorder[preStart] is the root
        for (;rootIndex <= inEnd; rootIndex++) {        // find its position in inorder array
            if (inorder[rootIndex] == preorder[preStart]) {
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // The rootIndex divides inorder array to two halves for the left and right subtree.
        // The range for left is [inStart, rootIndex-1], and right is [rootIndex+1, inEnd].
        // For left subtree, its root is the next element right after current root in preorder array,
        // so it is preorder+1.
        // To get the index for the root of the right subtree, we need to first calculate how many elements
        // are there in the left subtree, which is (rootIndex-inStart). Then we skip these elements to
        // get the first element in the right subtree's preorder array, preStart+(rootIndex-inStart)+1, 
        // which is the root
        root.left = builder(preorder, inorder, inStart, rootIndex-1, preStart+1);
        root.right = builder(preorder, inorder, rootIndex+1, inEnd, preStart+(rootIndex-inStart)+1);
        return root;
    }
}