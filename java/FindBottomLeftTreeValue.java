/*
 * Given a binary tree, find the leftmost value in the last row of the tree.
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

// Tree Level Traversal
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int depth = getDepth(root);
        int currentDepth = 1, count = 1, next = 0;
        TreeNode p;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (currentDepth < depth) {
            p = q.poll();
            count--;
            if (p.left != null) {
                q.offer(p.left);
                next++;
            }
            if (p.right != null) {
                q.offer(p.right);
                next++;
            }
            if (count == 0){
                currentDepth++;
                count = next;
                next = 0;
            }
        }
        
        return q.poll().val;
    }
    
    public int getDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}