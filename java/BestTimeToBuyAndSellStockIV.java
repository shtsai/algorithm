/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

// Solution 4:
// Add a quick solver for corner cases on leetcode.
// When k is greater than prices.length/2, we have enough number
// of transactions. We can calculate the max profits by simply
// summing up all prices increases in linear time.
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) return 0;
        if (k > prices.length / 2) {  // can quick solve because allow many transactions
            int res = 0;
            for (int i = 1; i < prices.length; i++) {   // simply sum up all prices increases
                if (prices[i] > prices[i-1]) {
                    res += prices[i]-prices[i-1];
                }
            }
            return res;
        }
        
        int[] dp = new int[prices.length];
        for (int i = 1; i <= k; i++) {      // i-th transaction
            int maxDiff = dp[0]-prices[0];
            for (int j = 1; j < prices.length; j++) {   // sell or not sell on jth day
                maxDiff = Math.max(maxDiff, dp[j]-prices[j]);
                dp[j] = Math.max(dp[j-1], maxDiff+prices[j]);
            }
        }
        return dp[prices.length-1];
    }
}

// Solution 3: Dynamic Programming (less space)
// Based on Solution 2, but only use 1D array
// Time: O(kn)
// Space: O(n)
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) return 0;
        int[] dp = new int[prices.length];
        for (int i = 1; i <= k; i++) {      // i-th transaction
            int maxDiff = Integer.MIN_VALUE;
            for (int j = 1; j < prices.length; j++) {   // sell or not sell on jth day
                maxDiff = Math.max(maxDiff, dp[j]-prices[j]);
                dp[j] = Math.max(dp[j-1], maxDiff+prices[j]);
            }
        }
        return dp[prices.length-1];
    }
}

// Solution 2: Dynamic Programming (fast version)
// Similar to Solution 1, but instead of trying all buy days,
// we maintain a maxDiff variable for each transaction.
// Observe that, for every buy day, we calculate prices[j]-prices[buy]+dp[i-1][buy].
// prices[j] is a constant here, and we just need the max value of (dp[i-1][buy]-prices[buy]).
// Therefore, we can use a variable to maintain the maximum of that value,
// and save one loop.
// Time: O(kn)
// Space: O(n^2)
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i <= k; i++) {      // i-th transaction
            int maxDiff = dp[i-1][0]-prices[0];
            for (int j = 1; j < prices.length; j++) {   // sell or not sell on jth day
                dp[i][j] = Math.max(dp[i][j-1], maxDiff+prices[j]);
                maxDiff = Math.max(maxDiff, dp[i-1][j]-prices[j]);
            }
        }
        return dp[k][prices.length-1];
    }
}



// Solution 1: Dynamic Programming (slow version)
// Create a 2-D DP array, where rows represent transactions,
// and columns represent days.
// dp[i][j] represents the maximum profit possible with i transactions
// until jth day.
// At each cell, we need to decide whether or not to make a transaction on
// that day. If we don't make a transaction, the max profit will be the same
// as the previous day, which is dp[i][j-1].
// If we are making a transaction, then we need to find a buy day (<j) to buy
// the stock so that we can sell it on jth day. In addition, we also need 
// to add the previous max profit with i-1 transactions until buy-th day.
//
// Time: O(kn^2) - Because we are filling a n^2 array, and for each cell we
// try at most n buy days, so there are three nested-loops
// Space: O(n^2) - 2D-array

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) return 0;
        int[][] dp = new int[k+1][prices.length];
        for (int i = 1; i <= k; i++) {      // i-th transaction
            for (int j = 1; j < prices.length; j++) {   // sell or not sell on jth day
                int localMax = 0;
                for (int buy = 0; buy < j; buy++) {     // try buying on every possible day
                    localMax = Math.max(localMax, prices[j]-prices[buy]+dp[i-1][buy]); // dp[i-1][buy] is the optimal result on previous transaction on buy-th day
                }
                dp[i][j] = Math.max(dp[i][j-1], localMax);
            }
        }
        return dp[k][prices.length-1];
    }
}