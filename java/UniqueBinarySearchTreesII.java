/*
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
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
// recursive solution, use a helper function to build BST from "start" to "end"
// At any given node, we try to use every node as the root,
// and we call this helper function get a list of subtrees for the left and right
// Then we combine lefts and rights to get all unique BSTs for this root and add them to the results.

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return generateHelper(1, n);
    }

    private List<TreeNode> generateHelper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {  // invalid indices, return null
            res.add(null);
            return res;
        }
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }
        for (int i = start; i <= end; i++) { // try to use every node as root
            List<TreeNode> lefts = generateHelper(start, i-1); // left subtrees
            List<TreeNode> rights = generateHelper(i+1, end); // right subtrees
            for (TreeNode left : lefts) { // get all combinations
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
