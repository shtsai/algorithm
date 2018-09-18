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
// Use three pointers:
//      - parent: points at the parent node
//      - nextParent: points at the next parent (in next iteration)
//      - child: points at the current child node, this is where we append to
// We then scan through the parent level, if we find a child, we append
// it to the child. 
// After we are done with the current level, move to next level and repeat.
//
// Time: O(n) - n is the number of nodes
// Space: O(1)
// 09/18/2018
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode parent = root;
        TreeLinkNode child = null;
        TreeLinkNode nextParent = null;
        while (parent != null) {
            while (parent != null) {
                if (parent.left != null) {
                    if (child == null) {
                        child = parent.left;
                        nextParent = child;
                    } else {
                        child.next = parent.left;
                        child = child.next;
                    }
                }
                if (parent.right != null) {
                    if (child == null) {
                        child = parent.right;
                        nextParent = child;
                    } else {
                        child.next = parent.right;
                        child = child.next;
                    }
                }
                parent = parent.next;
            }
            parent = nextParent;
            child = null;
            nextParent = null;
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