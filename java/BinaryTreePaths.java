/*
 * Given a binary tree, return all root-to-leaf paths.
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

// Solution 1 version 2: DFS
// add "->" right after each value, except for the leaf
// Much cleaner
// Time: O(n)
// Space: O(logn) - call stack

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        helper(result, "" + root.val, root);  //put in the root value
        return result;
    }
    
    public void helper(List<String> result, String s, TreeNode root) {
        if (root.left == null && root.right == null) { // the current node is a left
            result.add(s);  // add the string to the result
            return;
        }
        if (root.left != null) {
            helper(result, s + "->" + root.left.val, root.left); // recursively solve left
        }
        if (root.right != null) {
            helper(result, s + "->" + root.right.val, root.right); // recursively solve right
        }
    }
}

// Solution 1: DFS and StringBuilder
// 10/31/2017
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        
        StringBuilder sb = new StringBuilder();
        dfs(root, sb, res, true);        
        return res;
    }
    
    private void dfs(TreeNode node, StringBuilder sb, List<String> res, boolean isRoot) {
        int preLen = sb.length();
        if (isRoot) {
            sb.append(node.val);
        } else {
            sb.append("->" + node.val);
        }
        
        if (node.left == null && node.right == null) {  // find a leaf
            res.add(new String(sb));
        }
        if (node.left != null) {
            dfs(node.left, sb, res, false);
        }
        if (node.right != null) {
            dfs(node.right, sb, res, false);
        }
        sb.delete(preLen, sb.length());
    }
}