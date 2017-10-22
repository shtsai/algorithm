/*
    Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

    Example 1:
    Input:
        3
       / \
      9  20
        /  \
       15   7
    Output: [3, 14.5, 11]
    Explanation:
    The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
    Note:
    The range of node's value is in the range of 32-bit signed integer.
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

// Solution 1: Level-order traversal
// Perform level-order traversal, and compute the averge for each level.
// Time: O(n)
// Space: O(n)
// 10/21/2017

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int count = 1, count2 = 1, nextCount = 0;
        double sum = 0.0;
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            sum += node.val;
            count--;
            
            if (node.left != null) {
                q.offer(node.left);
                nextCount++;
            }
            if (node.right != null) {
                q.offer(node.right);
                nextCount++;
            }
            
            if (count == 0) {   // finish current level
                res.add(sum / count2);
                count = nextCount;
                count2 = nextCount;
                nextCount = 0;
                sum = 0.0;
            }
        }
        
        return res;
    }
}