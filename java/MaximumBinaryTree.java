/*
    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
    Construct the maximum tree by the given array and output the root node of this tree.

    Example 1:
    Input: [3,2,1,6,0,5]
    Output: return the tree root node representing the following tree:

          6
        /   \
       3     5
        \    / 
         2  0   
           \
            1
    Note:
    The size of the given array will be in the range [1,1000].
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

// Solution 1: recursion
// Time: O(n^2) - worse case: sorted array
// Space: O(1)
// 06/15/2018

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return treeBuilder(nums, 0, nums.length - 1);
    }
    
    public TreeNode treeBuilder(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        } else {
            int maxIndex = findMax(nums, left, right);
            TreeNode root = new TreeNode(nums[maxIndex]);
            root.left = treeBuilder(nums, left, maxIndex - 1);
            root.right = treeBuilder(nums, maxIndex + 1, right);
            return root;
        }
    }
    
    public int findMax(int[] nums, int left, int right) {
        int maxIndex = -1, max = Integer.MIN_VALUE;
        while (left <= right) {
            if (nums[left] > max) {
                maxIndex = left;
                max = nums[left];
            }
            left++;
        }
        return maxIndex;
    }
}