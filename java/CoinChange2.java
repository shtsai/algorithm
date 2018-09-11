/*
	You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

	Note: You can assume that

	0 <= amount <= 5000
	1 <= coin <= 5000
	the number of coins is less than 500
	the answer is guaranteed to fit into signed 32-bit integer
	Example 1:

	Input: amount = 5, coins = [1, 2, 5]
	Output: 4
	Explanation: there are four ways to make up the amount:
	5=5
	5=2+2+1
	5=2+1+1+1
	5=1+1+1+1+1
	Example 2:

	Input: amount = 3, coins = [2]
	Output: 0
	Explanation: the amount of 3 cannot be made up just with coins of 2.
	Example 3:

	Input: amount = 10, coins = [10] 
	Output: 1
 */

// Solution 3: Iterative DP
// DP[i][j] = # of ways for amount i using first j coins
// Base case:
//     DP[0][j] = 1   // empty set
// Recurrence relation:
//     DP[i][j] = DP[i][j-1] + DP[i - coins[j]][j]  
// Time: O(kn)
// Space: O(kn)
// 01/05/2018
class Solution {
    public int change(int amount, int[] coins) {
        int[][] count = new int[amount+1][coins.length+1];
        for (int i = 0; i < coins.length+1; i++) {
            count[0][i] = 1;
        }   
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                count[i][j] = count[i][j-1];
                if (i >= coins[j-1]) {
                    count[i][j] += count[i-coins[j-1]][j];
                }
            }
        }
        return count[amount][coins.length];
    }
}

// Solution 2: DP with memoization
// Time: O(kn) - k is target amount, n is number of coins
// Space: O(kn)
// 01/05/2018
class Solution {
    public int change(int amount, int[] coins) {
        int[][] memo = new int[amount+1][coins.length];
        return search(coins, amount, coins.length-1, memo);
    }
    
    public int search(int[] coins, int amount, int index, int[][] memo) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index < 0) return 0;
        if (memo[amount][index] != 0) return memo[amount][index];
        
        memo[amount][index] = search(coins, amount-coins[index], index, memo) // use current coin
                            + search(coins, amount, index-1, memo);			  // not to use current coin
        return memo[amount][index];
    }
}


// Solution 1: Brute force DFS
// Do DFS to find all possible combinations.
// Time: O(2^n) - time limit exceeded
// Space: O(n) - call stack
// 01/05/2018
class Solution {
    int count;
    
    public int change(int amount, int[] coins) {
        count = 0;
        search(coins, amount, 0);
        return count;
    }
    
    public void search(int[] coins, int amount, int index) {
        if (amount == 0) {
            count++;
            return;
        }
        if (amount < 0) return;
        for (int i = index; i < coins.length; i++) {
            search(coins, amount-coins[i], i);
        }
    }
}