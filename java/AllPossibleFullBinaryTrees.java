/*
	A full binary tree is a binary tree where each node has exactly 0 or 2 children.

	Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

	Each node of each tree in the answer must have node.val = 0.

	You may return the final list of trees in any order.

	Example 1:

	Input: 7
	Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

	Note:
	1 <= N <= 20
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
// Recursively solve problem.
// Time: O(2 ^ n)
// Space: O(2 ^ n)
// 08/25/2018

class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 != 1) {
            return res;
        }
        if (N == 1) {
            res.add(new TreeNode(0));
        } else {
            for (int i = 1; i < N; i += 2) {
                List<TreeNode> left = allPossibleFBT(i);
                List<TreeNode> right = allPossibleFBT(N - 1 - i);
                
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(0);
                        root.left = l;
                        root.right = r;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
}