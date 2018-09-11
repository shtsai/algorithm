/*
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1. 
 */

// Solution 3: Iterative Dynamic Programming
// Start exchange from amount 1 -> target.
// Try all coins, store min coins to get each amount.
// Then build result from previous subproblems.
// Time: O(kn) - k is the target amount, n is the number of coins
// Space: O(k) - store min change for each amount
// 09/11/2018
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i - c >= 0 && dp[i - c] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

// Solution 2: DFS + memoization
// Time: O(kn) - k is target amount, n is the number of coins
// Space: O(k)
// 09/11/2018
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount+1];
        int res = search(coins, amount, memo);
        return res;
    }
    
    public int search(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != 0) return memo[amount];
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = search(coins, amount - coin, memo);
            if (sub != -1) {
                min = Math.min(min, sub + 1);
            }
        }
        memo[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount];
    }
}

// Solution 1: Recursive 2D-DP with memoization
// DP[i, T]: use coins from [0: i] to get amout T
// = MIN{ DP[i, T - Ci] -> use coin i, reduce problem size, i doesn't change b/c can reuse
//		{ DP[i-1, T]    -> skip coin i, reduce coin size, target amount doesn't change
// Use memo array to store intermediate results.
// Time: O(kn) - n is the number of coins, k is the target amount
// Space: O(kn)
// 01/05/2018

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] memo = new int[coins.length+1][amount+1];
        for (int i = 0; i <= coins.length; i++) {	// initialize to -1
            Arrays.fill(memo[i], -1);
        }
        int res = search(coins, amount, coins.length, memo);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public int search(int[] coins, int amount, int i, int[][] memo) {
        if (amount == 0 && i >= 0) return 0;
        if (amount < 0 || i == 0) return Integer.MAX_VALUE;
        if (memo[i][amount] != -1) return memo[i][amount];
        int use = search(coins, amount-coins[i-1], i, memo); // use current coin
        int nouse = search(coins, amount, i-1, memo);		 // skip current coin
        memo[i][amount] = Math.min(use == Integer.MAX_VALUE ? use : use + 1, nouse);
        return memo[i][amount];
    }
}

