/*
	We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
	Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
	(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

	Example 1:
	Input: [1,null,0,0,1]
	Output: [1,null,0,null,1]
	 
	Explanation: 
	Only the red nodes satisfy the property "every subtree not containing a 1".
	The diagram on the right represents the answer.

	Example 2:
	Input: [1,0,1,0,0,0,1]
	Output: [1,null,1,null,1]

	Example 3:
	Input: [1,1,0,1,1,0,1,0]
	Output: [1,1,0,1,1,null,1]

	Note:
	The binary tree will have at most 100 nodes.
	The value of each node will only be 0 or 1.
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

// Solution 2: Recursion, one pass
// Use a helper function to determine if a subtree should be prune.
// The helper function also prunes the subtrees.
// Time: O(n)
// Space: O(1)
// 08/20/2018

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }
    
    private boolean containsOne(TreeNode node) {
        if (node == null) 
            return false;
    
        boolean left = containsOne(node.left);
        if (!left) {
            node.left = null;
        }
        
        boolean right = containsOne(node.right);
        if (!right) {
            node.right = null;
        }
            
        return node.val == 1 || left || right;
    }
}

// Solution 1:
// Two passes.
// First pass: traverse the tree to find nodes to be pruned.
// Second pass: traverse the tree again to prune.
//
// Time: O(n)
// Space: O(n) - HashMap
// 08/20/2018

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        HashMap<TreeNode, Boolean> map = new HashMap<>();
        map.put(null, false);
        traverse(root, map);
        return prune(root, map);
    }
    
    private boolean traverse(TreeNode root, HashMap<TreeNode, Boolean> map) {
        if (map.containsKey(root)) {
            return map.get(root);
        }
        boolean left = traverse(root.left, map);
        boolean right = traverse(root.right, map);
        boolean self = root.val == 1 ? true : false;
        boolean res = self || left || right;
        map.put(root, res);
        return res;
    }
    
    private TreeNode prune(TreeNode root, HashMap<TreeNode, Boolean> map) {
        if (root == null || !map.get(root)) {
            return null;
        }
        root.left = prune(root.left, map);
        root.right = prune(root.right, map);
        return root;
    }
}