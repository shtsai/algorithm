/*
    Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

    Example 1:
    Given tree s:

         3
        / \
       4   5
      / \
     1   2
    Given tree t:
       4 
      / \
     1   2
    Return true, because t has the same structure and node values with a subtree of s.
    Example 2:
    Given tree s:

         3
        / \
       4   5
      / \
     1   2
        /
       0
    Given tree t:
       4
      / \
     1   2
    Return false.
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

// Solution 2: recursive check, much more concise
// Same idea as solution 1, but check in a recursive manner.
// Much faster performance
// Time: O(m * n) - len(s) = m, len(t) = n
// Space: O(m) - the depth of recursion tree can go upto m
// reference: https://leetcode.com/articles/subtree-of-another-tree/
// 10/21/2017

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return check(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && check(s.left, t.left) && check(s.right,t.right);
    }
}

// Solution 1: recursion + BFS
// Main function looks for nodes that have the same root value.
// If that is the case, check if it is valid subtree.
// Otherwise, recursively check left and right children.
// Time: O(n(m+n)) - main function check all n nodes, 
//                   and helper function checks all m + n nodes in the two trees.
// Space: O(n) - queue
// 10/21/2017

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        if (s.val == t.val && checkSubtree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean checkSubtree(TreeNode s, TreeNode t) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(s);
        q2.offer(t);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode a = q1.poll();
            TreeNode b = q2.poll();
            if (a.val != b.val) return false;
            if (a.left != null && b.left != null) {
                q1.offer(a.left);
                q2.offer(b.left);
            } else if (a.left != b.left) {  // need to be both null
                return false;
            }
            if (a.right != null && b.right != null) {
                q1.offer(a.right);
                q2.offer(b.right);
            } else if (a.right != b.right) {  // need to be both null
                return false;
            }
        }
        
        return q1.isEmpty() && q2.isEmpty();
    }
}