/*
	Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

	Example 1:
	Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

	       5
	      / \
	    3    6
	   / \    \
	  2   4    8
	 /        / \ 
	1        7   9

	Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

	 1
	  \
	   2
	    \
	     3
	      \
	       4
	        \
	         5
	          \
	           6
	            \
	             7
	              \
	               8
	                \
	                 9  
	Note:
	The number of nodes in the given tree will be between 1 and 100.
	Each node will have a unique integer value from 0 to 1000.
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

// Solution 2:
// Use a global variable to store current node.
// Linking the new Tree while doing inorder traversal.
// Time: O(n)
// Space: O(h) - stack space
// 09/02/2018

class Solution {
    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        cur = dummy;
        inorder(root);
        return dummy.right;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        root.left = null;
        cur.right = root;
        cur = cur.right;
        inorder(root.right);
    }
}

// Solution 1:
// First do in-order traversal and store all the nodes in a list.
// Then build tree from the list.
// Time: O(n)
// Space: O(n)
// 09/01/2018

class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        return buildTree(nodes, 0);
    }
    
    private void inorder(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return;
        }
        inorder(root.left, nodes);
        nodes.add(root);
        inorder(root.right, nodes);
    }
    
    private TreeNode buildTree(List<TreeNode> nodes, int index) {
        if (index == nodes.size()) {
            return null;
        }
        TreeNode root = nodes.get(index);
        root.left = null;
        root.right = buildTree(nodes, index + 1);
        return root;
    }
}