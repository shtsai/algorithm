/*
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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
// Solution 2: preSum approach
// DFS + HashMap
// use a HashMap to store the preSum for paths from root to previous nodes
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);       // base case, there is one way to obtain 0 presum
        return helper(root, 0, sum, preSum);
    }
    
    // a helper function that finds paths ending at the current node
    private int helper(TreeNode root,int curSum,int target,HashMap<Integer,Integer> preSum) {
        int res = 0;
        if (root == null) return res;
        curSum += root.val;     // update curSum
        // find a path, from the end node on preSum to current node
        if (preSum.containsKey(curSum-target)) {    
            res += preSum.get(curSum-target);
        }
        // store current sum
        if (preSum.containsKey(curSum)) preSum.put(curSum, preSum.get(curSum)+1);
        else preSum.put(curSum, 1);

        // continue searching children in a DFS way
        res += helper(root.left,curSum,target,preSum);   
        res += helper(root.right,curSum,target,preSum);
        
        // finish searching current node, remove the presum from root to current node
        preSum.put(curSum, preSum.get(curSum)-1);
        return res;
    }
}


// Solution 1: DFS, recursively check for every node
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumThrough(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    // this helper function checks for path thats goes through the input node
    public int pathSumThrough(TreeNode root, int sum) {
        if (root == null) return 0;
        int result = 0;
        if (root.val == sum) result++;
        return result + pathSumThrough(root.left, sum - root.val) + pathSumThrough(root.right, sum - root.val);
    }
}