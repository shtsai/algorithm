/*
        Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

        For example:
        Given binary tree [3,9,20,null,null,15,7],
            3
           / \
          9  20
            /  \
           15   7
        return its bottom-up level order traversal as:
        [
          [15,7],
          [9,20],
          [3]
        ]
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

// Solution 2: DFS
// Same as Binary Tree Level Order Traversal I
// except adding the lists in reverse order.
// Time: O(n)
// Space: O(logn) - call stack
// 10/31/2017

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode node, List<List<Integer>> res, int level) {
        if (node == null) return;
        if (level >= res.size()) {
            res.add(0, new LinkedList<>());
        }
        res.get(res.size()-1-level).add(node.val);
        dfs(node.left, res, level+1);
        dfs(node.right, res, level+1);
    }
}

// Solution 1: BFS
// Same as Binary Tree Level Order Traversal I
// except adding the lists in reverse order.
// Time: O(n)
// Space: O(n)
// 10/31/2017

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int count = 1, next = 0;
        List<Integer> list = new LinkedList<>();
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (p.left != null) {
                q.offer(p.left);
                next++;
            }
            if (p.right != null) {
                q.offer(p.right);
                next++;
            }
            
            list.add(p.val);
            count--;
            if (count == 0) {
                res.add(0, list);
                count = next;
                next = 0;
                list = new LinkedList<>();
            }
        }
        return res;
    }
}