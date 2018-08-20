/*
    Return any binary tree that matches the given preorder and postorder traversals.
    Values in the traversals pre and post are distinct positive integers.

    Example 1:
    Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
    Output: [1,2,3,4,5,6,7]

    Note:
    1 <= pre.length == post.length <= 30
    pre[] and post[] are both permutations of 1, 2, ..., pre.length.
    It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 *

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Solution 2: Divide and conquer
// Find the range of the subtrees, and recursively build them

// Time: O(n)
// Space: O(n)
// 08/19/2018

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        HashMap<Integer, Integer> postIndex = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postIndex.put(post[i], i);
        }
        return builder(pre, post, 0, pre.length - 1, 0, postIndex);
    }

    private TreeNode builder(int[] pre, int[] post, int prestart, int preend, int poststart, HashMap<Integer, Integer> postIndex) {
        if (prestart == preend) {
            return new TreeNode(pre[prestart]);
        } else if (prestart > preend) {
            return null;
        }
        TreeNode root = new TreeNode(pre[prestart]);
        int leftEnd = postIndex.get(pre[prestart + 1]);
        int leftLen = leftEnd - poststart + 1;
        root.left = builder(pre, post, prestart + 1, prestart + leftLen, poststart, postIndex);
        root.right = builder(pre, post, prestart + leftLen + 1, preend, leftEnd + 1, postIndex);
        return root;
    }
}

// Solution 1: Recursive Traversal
// Use two pointers (i, j) to keep track of the current index at two arrays.
// Create a new node when visiting pre[i]
// If the value of current node == post[i],
// that means the current node is finished, and we return the current node.
//
// Time: O(n)
// Space: O(1)
// 08/18/2018

class Solution {
    int i;
    int j;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        i = 0;
        j = 0;
        return buildTree(pre, post);
    }

    private TreeNode buildTree(int[] pre, int[] post) {
        if (i == pre.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i++]);
        if (root.val == post[j]) {
            j++;
            return root;
        }
        root.left = buildTree(pre, post);
        if (root.val == post[j]) {
            j++;
            return root;
        }
        root.right = buildTree(pre, post);
        if (root.val == post[j]) {
            j++;
        }
        return root;
    }
}
