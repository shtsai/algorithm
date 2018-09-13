/*
 * Given inorder and postorder traversal of a tree, construct the binary tree.
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

// Solution 1:
// Similar to (105) Construct Binary Tree from Preorder and Inorder Traversal
// Time: O(n)
// Space: O(n) - stack

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        return builder(inorder, postorder, 0, inorder.length-1, inorder.length-1);
    }
    
    private TreeNode builder(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart) {
        if (inStart > inEnd || inStart >= postorder.length) return null;
        int rootIndex = inStart;
        // postorder[postStart] is the root, rootIndex is the index of root in inorder array
        while (rootIndex <= inEnd && inorder[rootIndex] != postorder[postStart]) {
            rootIndex++;     
        }
        TreeNode root = new TreeNode(postorder[postStart]);
        // (inEnd - rootIndex) = number of nodes in the right subtree
        // postStart-(inEnd-rootIndex)-1 gives you the last node of the left subtree, which is its root
        root.left = builder(inorder, postorder, inStart, rootIndex-1, postStart-(inEnd-rootIndex)-1);
        root.right = builder(inorder, postorder, rootIndex+1, inEnd, postStart-1);
        return root;
    }
}