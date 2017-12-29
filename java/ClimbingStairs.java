/*
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */

// Solution 4:
// Dynamic Programming, bottom-up solution
// only store two integers, b/c we only need the result from last two steps
// Time: O(n)
// Space: O(1)
// Time: 12/29/2017
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}

// Solution 3:
// Iterative solution with memoization
// time: O(n), space O(n)
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n+1];
        dp[1] = 1;      // initialzie base cases
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}

// Solution 2: Recursive solution with memoization
// Time: O(n)
// Space: O(n) - memo array
// 12/29/2017
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        return climb(n, memo);
    }
    
    public int climb(int n, int[] memo) {
        if (memo[n] != 0) return memo[n];
        memo[n] = climb(n-2, memo) + climb(n-1, memo);
        return memo[n];
    }
}

// Solution 1: Recursive solution, top down
// Time: O(2^n)
// Space: O(n) - call stack
// 12/29/2017
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n-2) + climbStairs(n-1);
    }
}