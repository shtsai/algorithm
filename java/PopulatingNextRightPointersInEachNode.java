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

// Solution 3: General solution
// similar to PopulatingNextRightPointersInEachNodeII
// Time: O(n)
// Space: O(1)
// 09/18/2018
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode parent = root;
        TreeLinkNode child;
        while (parent.left != null) {
            TreeLinkNode nextParent = parent.left;
            child = parent.left;
            while (parent != null) {
                child.next = parent.right;
                child = child.next;
                parent = parent.next;
                if (parent != null && parent.left != null) {
                    child.next = parent.left;
                    child = child.next;
                }
            }
            parent = nextParent;
        }
    }
}

// Solution 2:
// Since we have perfect binary tree as input, we only
// need to keep track of the first node of every level.
// Then we can use a point to scan through the parent node,
// and connect children on the next level.
// After we are done, going to the children level, and repeat.
// run the algorithm on an example to understand visually
//
// Time: O(n) - n is the number of nodes
// Space: O(1)
// 11/13/2017

public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode parent = root;
        TreeLinkNode parentNext;
        while (parent.left != null) {
            parentNext =  parent.left;
            while (parent != null) {
                parent.left.next = parent.right;
                if (parent.next != null) {
                    parent.right.next = parent.next.left;
                }
                parent = parent.next;
            }
            parent = parentNext;
        }    
    }
}

// Solution 1:
// level order traversal
// Connect nodes on the same level
// Time: O(n)
// Space: O(n) - queue
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