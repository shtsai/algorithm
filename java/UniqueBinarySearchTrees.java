/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 */

// dynamic programming
// Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number i in the sequence, and use the number as the root, naturally, the subsequence 1…(i-1) on its left side would lay on the left branch of the root, and similarly the right subsequence (i+1)…n lay on the right branch of the root. We then can construct the subtree from the subsequence recursively.
// reference: https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i

public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];        // dp array
        dp[0] = 1;          // base case
        
        for (int i = 1; i <= n; i++) {  // build the array from left to right
            for (int j = 1; j <= i; j++) {  // use 1...i as the root
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        
        return dp[n];
    }
}