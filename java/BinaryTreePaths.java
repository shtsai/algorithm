/*
    Given a binary tree, return all root-to-leaf paths.

    Note: A leaf is a node with no children.

    Example:
    Input:

       1
     /   \
    2     3
     \
      5

    Output: ["1->2->5", "1->3"]
    Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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

// Solution 3: DFS + stack
// Time: O(n)
// Space: O(logn)
// 09/11/2018
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Stack<TreeNode> parents = new Stack<>();
        Stack<String> strings = new Stack<>();
        parents.push(root);
        strings.push(Integer.toString(root.val));        
            
        while (!parents.isEmpty()) {
            TreeNode node = parents.pop();
            String cur = strings.pop();
            if (node.left == null && node.right == null) {
                res.add(cur);
            } 
            if (node.right != null) {
                parents.push(node.right);
                strings.push(cur + "->" + node.right.val);
            }
            if (node.left != null) {
                parents.push(node.left);
                strings.push(cur + "->" + node.left.val);
            }
        }
        return res;
    }    
}

// Solution 2: BFS + queue
// Use two queues to store next node and corresponding string
// Time: O(n)
// Space: O(logn)
// 09/11/2018
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Queue<TreeNode> parents = new LinkedList<>();
        Queue<String> strings = new LinkedList<>();
        parents.offer(root);
        strings.offer(Integer.toString(root.val));        
            
        while (!parents.isEmpty()) {
            TreeNode node = parents.poll();
            String cur = strings.poll();
            if (node.left == null && node.right == null) {
                res.add(cur);
            } else {
                if (node.left != null) {
                    parents.offer(node.left);
                    strings.offer(cur + "->" + node.left.val);
                }
                if (node.right != null) {
                    parents.offer(node.right);
                    strings.offer(cur + "->" + node.right.val);
                }
            }
        }
        
        return res;
    }    
}

// Solution 1: DFS
// add "->" right after each value, except for the leaf
// Time: O(n)
// Space: O(logn) - call stack
// 09/11/2018
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, res, "");
        return res;
    }
    
    private void dfs(TreeNode node, List<String> res, String s) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            res.add(new String(s + node.val));            
        } else {
            dfs(node.left, res, s + node.val + "->");
            dfs(node.right, res, s + node.val + "->");
        }
    }
}