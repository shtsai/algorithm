/*
    Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

    Example 1:
    Input: 
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 9

    Output: True
    Example 2:
    Input: 
        5
       / \
      3   6
     / \   \
    2   4   7

    Target = 28

    Output: False
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

// Solution 3: Inorder Traversal + two pointers
// Perform inorder traversal to get a sorted array.
// Then use two pointers to do two sum.
// Time: O(n) - inorder + two sum
// Space: O(n) - array
// 10/17/2017

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        int left = 0, right = list.size()-1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
    
    private void inorder(TreeNode node, ArrayList<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}


// Solution 2: HashSet + BFS
// Use BFS to traverse the tree, at each node, 
// check if (target-val) exists in the set.
// If so, return true. Otherwise, continue searching.
// Time: O(n) - BFS traversal
// Space: O(n) - set + queue
// 10/17/2017

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        HashSet<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (set.contains(k-p.val) && k-p.val != p.val) return true;
            set.add(p.val);
            if (p.left != null) q.offer(p.left);
            if (p.right != null) q.offer(p.right);
        }
        return false;
    }
}

// Solution 1: BFS + Binary search
// Use BFS to traverse the tree, at each node, use binary search
// to check if (target-val) exists in the tree.
// If so, return true. Otherwise, continue searching.
// Time: O(nlogn) - n for traverse all nodes, logn for binary search
//                - very fast on leetcode OJ (98%)
// Space: O(n + logn) - queue of size n + logn of call stacks
// 10/17/2017

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (binarySearch(root, k-p.val) && k - p.val != p.val) {
                return true;
            }
            if (p.left != null) q.offer(p.left);
            if (p.right != null) q.offer(p.right);
        }
        return false;
    }
    
    private boolean binarySearch(TreeNode root, int k) {
        if (root == null) {
            return false;
        } else if (root.val == k) {
            return true;
        } else if (root.val > k) {
            return binarySearch(root.left, k);
        } else {
            return binarySearch(root.right, k);
        }
        
    }
}

