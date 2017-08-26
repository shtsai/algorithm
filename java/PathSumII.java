/*
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
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

// recursion, backtrace, DP-like solution
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        pathFinder(root, sum, res);
        return res;
    }
    // a helper function that returns valid sum paths 
    // build the paths bottom-up
    private void pathFinder(TreeNode root, int sum, List<List<Integer>> res) {
        if (root == null) return;
        if (root.val == sum && root.left == null && root.right == null) {  // reach leaf
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
            return;
        }
        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        pathFinder(root.left, sum-root.val, left);
        pathFinder(root.right, sum-root.val, right);

        for (List<Integer> list : left) {
            list.add(0, root.val);      // insert current node to the head of the path
            res.add(list);
        }
        for (List<Integer> list : right) {
            list.add(0, root.val);
            res.add(list);
        }
    }
}