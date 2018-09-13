/*
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * For example:
 * Given the following binary tree,
 *       1            <---
 *     /   \
 *    2     3         <---
 *     \     \
 *      5     4       <---
 * You should return [1, 3, 4].
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

// Solution 2: DFS
// DFS, use a variable to keep track of the current level
// Since we do root -> right -> left
// if a node is the first node visited at the current level,
// we add it to the result list
// Time: O(n)
// Space: O(n)
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        result.add(root.val);
        int currentLevel = 0; // root is at level 0
        helper(result, root.right, currentLevel+1);  // visit right before left
        helper(result, root.left, currentLevel+1);
        return result;
    }
    
    public void helper(List<Integer> result, TreeNode node, int currentLevel) {
        if (node == null) return;
        if (currentLevel == result.size()){   // this is the first node visited at this level
            result.add(node.val);             // it is the right most node, and add it to result list
        }
        helper(result, node.right, currentLevel+1);
        helper(result, node.left, currentLevel+1);
    } 
}


// Solution 1: BFS
// BFS, add the last nodes in each level
// Time: O(n)
// Space: O(n)
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        int n = 1;   // the number of nodes on the first level is 1
        q.offer(root);  // add root to the queue
        
        while (!q.isEmpty()) {
            TreeNode p = null;
            for (int i = 0; i < n; i++) {   // iterate through all the nodes on the current level
                p = q.poll();
                if (p.left != null) {       // add the their child nodes
                    q.offer(p.left);
                }
                if (p.right != null) {
                    q.offer(p.right);
                }
            }
            list.add(p.val);                // add the last node on the current level to the result list
            n = q.size();                   // update the number of elements in the next level
        }
        return list;
    }
}