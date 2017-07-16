/*
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note: 
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 * Example:
 * 
 * Given [3, 1, 5, 8]
 * 
 * Return 167
 * 
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */

// dynamic programming
public class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length + 2;
        int[] fullNums = new int[len];      // fullNums adds two boundary element '1' to the original array
        fullNums[0] = fullNums[len-1] = 1;
        for (int i = 0; i < len-2; i++) {
            fullNums[i+1] = nums[i];
        }
        
        int[][] dp = new int[len][len];    // dp[i][j] = the maxCoins obtained from subarray [i, j]
        return helper(fullNums, dp, 0, len-1);
    }
    
    private int helper(int[] N, int[][] dp, int start, int end) {
        if (start > end) return 0;
        if (dp[start][end] != 0) return dp[start][end];   // return optimal value if already exists
        
        for (int i = start + 1; i < end; i++) {     // try different break points i, i is the last balloon to burst
            dp[start][end] = Math.max(dp[start][end], N[start]*N[i]*N[end] + helper(N, dp, start, i) + helper(N, dp, i, end));
        }
        return dp[start][end];
    }
}