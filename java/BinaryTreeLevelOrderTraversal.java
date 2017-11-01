/*
        Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

        For example:
        Given binary tree [3,9,20,null,null,15,7],
            3
           / \
          9  20
            /  \
           15   7
        return its level order traversal as:
        [
          [3],
          [9,20],
          [15,7]
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
// Use DFS with a parameter level.
// level will indicates the index of the list to which the node should be added
// Recursively add each node to the list of its level.
// Time: O(n)
// Space: O(logn) - call stack
// 10/31/2017

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (level >= res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        dfs(root.left, res, level+1);
        dfs(root.right, res, level+1);
    }
}

// Solution 1: BFS
// Use a queue to perform BFS.
// Needs to keep track of number of nodes at each level and next level.
// Time: O(n) - scan each node exactly once
// Space: O(n) - queue
// 10/31/2017

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