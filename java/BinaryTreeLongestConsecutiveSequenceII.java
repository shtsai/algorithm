/*
	Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

	Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

	Example 1:
	Input:
	        1
	       / \
	      2   3
	Output: 2
	Explanation: The longest consecutive path is [1, 2] or [2, 1].
	
	Example 2:
	Input:
	        2
	       / \
	      1   3
	Output: 3
	Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
	Note: All the values of tree nodes are in the range of [-1e7, 1e7].
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
// Use a helper function that returns a pair.
// 	 - res[0]: length of increasing path through the node
//   - res[1]: length of decreasing path through the node
// Add each node, we call helper function on its left and right child.
// Then we check if we extend the paths, and update max.
//
// Time: O(n)
// Space: O(n)
// 09/05/2018

class Solution {
    int max;
    public int longestConsecutive(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    
    // res is the return value
    // res[0]: length of increasing path
    // res[1]: length of decreasing path
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] res = new int[] {1, 1};
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                res[0] = Math.max(res[0], left[0] + 1);
            } else if (root.left.val == root.val - 1) {
                res[1] = Math.max(res[1], left[1] + 1);
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                res[0] = Math.max(res[0], right[0] + 1);
            } else if (root.right.val == root.val - 1) {
                res[1] = Math.max(res[1], right[1] + 1);
            }
        }
        
        max = Math.max(max, res[0] + res[1] - 1);
        return res;
    }
}