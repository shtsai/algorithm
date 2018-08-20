/*
	Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

	Example:
	Given binary tree 
	          1
	         / \
	        2   3
	       / \     
	      4   5    
	Returns [4, 5, 3], [2], [1].

	Explanation:
	1. Removing the leaves [4, 5, 3] would result in this tree:
	          1
	         / 
	        2          

	2. Now removing the leaf [2] would result in this tree:
	          1          

	3. Now removing the leaf [1] would result in the empty tree:
	          []         

	Returns [4, 5, 3], [2], [1].
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

// Solution 2: DFS
// Use a recursive helper function
// Base case: null -> -1
// Normal case: find its current level (by looking at left and right)
//
// Time: O(n)
// Space: O(1)
// 08/20/2018

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        finder(res, root);
        return res;
    }
    
    // add current node to its level, and return current level index
    private int finder(List<List<Integer>> res, TreeNode node) {
        if (node == null) {
            return -1;
        } else {
            int left = finder(res, node.left);
            int right = finder(res, node.right);
            int current = Math.max(left, right) + 1;
            if (res.size() <= current) {
                res.add(new ArrayList<Integer>());
            }
            res.get(current).add(node.val);
            return current;
        }
    }
}

// Solution 1: Naive solution
// Remove leaves layer by layer.
// Time: O(n ^ 2) - unbalanced tree
// Space: O(1)
// 08/20/2018

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while (root != null) {
            root = removeLeaves(root, res, new ArrayList<Integer>());
        }
        return res;
    }
    
    private TreeNode removeLeaves(TreeNode root, List<List<Integer>> res, List<Integer> current) {
        boolean isLeaf = helper(root, current);
        res.add(current);
        return isLeaf ? null : root;
    }
    
    // Return true if node is a leaf
    private boolean helper(TreeNode node, List<Integer> current) {
        if (node == null) {
            return true;
        } else if (node.left == null && node.right == null) {
            current.add(node.val);
            return true;
        } else {
            if (helper(node.left, current)) {
                node.left = null;
            }
            if (helper(node.right, current)) {
                node.right = null;
            }
            return false;
        }
    }
}