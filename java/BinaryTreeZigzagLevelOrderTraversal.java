/*
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
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

// Solution 1:
// level order traversal, reverse even levels
// Time: O(n)
// Space: O(n)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int count = 1, nextCount = 0, level = 1;
        List<Integer> list = new ArrayList<>();
        while (count > 0) {
            TreeNode p = q.poll();
            list.add(p.val);
            count--;
            if (p.left != null) {
                q.offer(p.left);
                nextCount++;
            }
            if (p.right != null) {
                q.offer(p.right);
                nextCount++;
            }
            if (count == 0) {   // finish a level
                count = nextCount;
                nextCount = 0;
                if (level % 2 == 0) {  // if on a even level, need to reverse
                    Collections.reverse(list);
                }
                res.add(list);
                list = new ArrayList<>();
                level++;
            }
        }
        return res;
    }
}

// Solution 2:
// DFS, add nodes to their corresponding level during visits
// Time: O(n)
// Space: O(n)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        visit(root, res, 0);
        return res;
    }
    
    private void visit(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() <= level) {// if the list for current level is not initialized
            res.add(new LinkedList<>());
        }
        List<Integer> list = res.get(level);    // get the list for current level
        if (level % 2 == 0) {       // even levels (0, 2, 4, ...), left -> right
            list.add(root.val);
        } else {                    // odd levels (1, 3, 5, ...), left <- right
            list.add(0, root.val);
        }
        // recursively visit children
        visit(root.left, res, level+1);
        visit(root.right, res, level+1);
    }
}