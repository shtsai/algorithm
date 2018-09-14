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
// First do in-order traversal to find the two misplaced nodes.
// Then swap the value of these two nodes.
// reference: https://discuss.leetcode.com/topic/3988/no-fancy-algorithm-just-simple-and-powerful-in-order-traversal
// Time: O(n)
// Space: O(1)

class Solution {
    TreeNode prev, first, second;

    public void recoverTree(TreeNode root) {
        prev = new TreeNode(Integer.MIN_VALUE);
        first = second = null;
        recover(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /* a helper function that use in-order traversal to find
       first and second misplaced nodes */
    private void recover(TreeNode node) {
        if (node == null) return;
        recover(node.left);

        if (first == null && prev.val > node.val) {
            first = prev;
        }
        if (first != null && prev.val > node.val) {
            second = node;
        }
        prev = node;

        recover(node.right);
    }
}
