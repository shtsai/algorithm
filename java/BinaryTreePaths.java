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

/*
// use StringBuilder
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(result, sb, root);
        return result;
    }
    
    public void helper(List<String> result, StringBuilder sb, TreeNode root) {
        if (root == null) {
            return;   
        }
        if (root.left == null && root.right == null) {
            int len = sb.length();
            if (len != 0) sb.append("->"+root.val);
            else sb.append(root.val);
            result.add(sb.toString());
            sb.delete(len, sb.length());
            return;
        }
        if (root.left != null) {
            int len = sb.length();
            if (len != 0) sb.append("->"+root.val);
            else sb.append(root.val);
            helper(result, sb, root.left);
            sb.delete(len, sb.length());
        }
        if (root.right != null) {
            int len = sb.length();
            if (len != 0) sb.append("->"+root.val);
            else sb.append(root.val);
            helper(result, sb, root.right);
            sb.delete(len, sb.length());
        }
    }
}
*/