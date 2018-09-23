/*
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

// Solution 2: In place
// Time: O(n) - n is the number of nodes
// Space: O(1)
// 09/23/2018
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        while (root != null) {
            TreeLinkNode dummy = new TreeLinkNode(-1);
            TreeLinkNode child = dummy;
            while (root != null) {
                if (root.left != null) {
                    child.next = root.left;
                    child = child.next;
                }
                if (root.right != null) {
                    child.next = root.right;
                    child = child.next;
                }
                root = root.next;
            }
            root = dummy.next;
            dummy.next = null;
        }
    }
}

// Solution 1: Level order traversal
// Time: O(n)
// Space: O(n)
// 09/18/2018

public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);
        TreeLinkNode prev = null;
        while (!q.isEmpty()) {
            int count = q.size();
            while (count > 0) {
                TreeLinkNode p = q.poll();
                count--;
                if (prev == null) {
                    prev = p;
                } else {
                    prev.next = p;
                    prev = p;
                }
                if (p.left != null) {
                    q.offer(p.left);
                }
                if (p.right != null) {
                    q.offer(p.right);
                }
            }
            prev = null;
        }
    }
}