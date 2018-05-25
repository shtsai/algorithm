/*
    Given a binary tree, count the number of uni-value subtrees.
    A Uni-value subtree means all nodes of the subtree have the same value.

    Example :
    Input:  root = [5,1,5,5,5,null,5]
                5
               / \
              1   5
             / \   \
            5   5   5

    Output: 4
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

// Solution 1: Top down
// Use a helper function that checks if the current node is the
// root of a univalue subtree with target value, and the 
// total number of univalue subtrees under that node.
// Time: O(n)
// Space: O(1)
// 05/25/2018

class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, root.val)[1];
    }
    
    // A helper function that returns a result pair of two values:
    //     - result[0]: 1 if the the node is the root of a univalue tree with target
    //                  0 otherwise
    //     - result[1]: # of univalue subtrees under the given node
    private int[] helper(TreeNode root, int target) {
        int[] res = new int[2];
        if (root == null) {
            res[0] = 1;
        } else {
            // recursively check left and right children
            int[] left = helper(root.left, root.val);
            int[] right = helper(root.right, root.val);
            
            // if left and right children match, 
            // the current node is also a root of a univalue subtree
            if (left[0] == 1 && right[0] == 1) {
                res[1] += 1;
                if (target == root.val) {
                    res[0] = 1;
                }
            }
            
            // add the result from left and right children
            res[1] += left[1] + right[1];
        }
        
        return res;
    }
}

// Solution 2: Bottom up
// Use a global variable and helper function.
// Time: O(n)
// Space: O(1)
// 05/25/2018
class Solution {
    int res;
    
    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }
    
    // Returns true if the current node is a root of a univalue tree
    // Increment counter by one when find a univalue tree.
    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            boolean left = helper(root.left);
            boolean right = helper(root.right);
            if (left && right) {
                if (root.left != null && root.left.val != root.val) {
                    return false;
                } else if (root.right != null && root.right.val != root.val) {
                    return false;
                } else {
                    res += 1;
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}