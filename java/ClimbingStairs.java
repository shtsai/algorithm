/*
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */

// Solution 2:
// Dynamic Programming
// only store two integers, b/c we only need the result from last two steps
// time: O(n), space: O(1)
public class Solution {
    public int climbStairs(int n) {
        int first = 1;
        int second = 2;
        if (n == 0 || n == 1) return 1;

        for (int i = n - 2; i > 0; i--) {
            int steps = first + second;
            first = second;
            second = steps;
        }

        return second;
    }
}

// Solution 1:
// use an array for memoization
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
