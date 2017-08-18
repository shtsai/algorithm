/*
 * Given a binary tree
 * 
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

// Solution 2:
// use pointers to connect left child and right child
// run the algorithm on an example to understand visually
// constant space, faster
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        
        TreeLinkNode prev = root, cur = null;
        while (prev.left != null) {      // next level exists (b/c perfect binary tree)
            cur = prev;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            prev = prev.left;
        }

        return;
    }
}

// Solution 1:
// level order traversal
// space: O(n)  (queue)
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        
        int count = 1, next = 0;      // start from top level = 1
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            // first do level order traversal
            TreeLinkNode p = q.poll();
            if (p.left != null) q.offer(p.left); next++;
            if (p.right != null) q.offer(p.right); next++;
            count--;
            
            // handle last node on each level
            if (count == 0) {
                p.next = null;
                count = next;
                next = 0;
            } else {
                p.next = q.peek();
            }
        }
        return;
    }
}