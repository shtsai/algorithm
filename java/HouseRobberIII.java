/* 
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 
 * Example 1:
 *      3
 *     / \
 *    2   3
 *     \   \ 
 *      3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *      3
 *     / \
 *    4   5
 *   / \   \ 
 *  1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
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

// dynamic programming
// use a helper function that returns the optimal results of a subproblem under two senarios
// res[0]: the optimal result we get if we don't rob this house
// res[1]: the optimal result we get if we rob this house
// then we use recursion to build up solution for the original problem
class Solution {
    public int rob(TreeNode root) {
        int[] result = rob2(root);
        return Math.max(result[0], result[1]);
    }
    
    private int[] rob2(TreeNode node) {
        int[] res = new int[2];         
        if (node == null) return res;
        
        int[] left = rob2(node.left);
        int[] right = rob2(node.right);
        // if we don't rob this house, we can either rob or not rob its children
        // simply find the maximum combination
        res[0] = Math.max(left[0],left[1]) + Math.max(right[0], right[1]);
        res[1] = node.val + left[0] + right[0];
        return res;
    }
}