/*
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
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
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> list = new ArrayList<>();
        int count = 1, next = 0;
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            list.add(n.val);
            if (n.left != null) { 
                q.offer(n.left);
                next++;
            }
            if (n.right != null) {
                q.offer(n.right);
                next++;
            }
            count--;
            
            if (count == 0) {  // current level is finished, move on to next level
                result.add(list);
                if (next > 0) {
                    count = next;
                    next = 0;
                    list = new ArrayList<Integer>();
                }
            }
        }
        
        return result;
    }
}