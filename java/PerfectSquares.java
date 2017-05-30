/*
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */

// Dynamic Programming
public class Solution {
    public int numSquares(int n) {
        if (n >= 0 && n <= 3) return n;
        int[] dp = new int[n+1];  // dp array

        // base case
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int num = 4; num <= n; num++) {
            int result = Integer.MAX_VALUE;
            int factor = (int) Math.sqrt(num);
            for (int i = factor; i > 0; i--) {
                if (num-i*i < 0) continue;
                result = Math.min(result, dp[num-i*i]+1);
            }
            dp[num] = result;
        }
        
        return dp[n]; 
    }
}