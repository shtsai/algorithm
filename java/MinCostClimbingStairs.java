/*
	On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

	Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

	Example 1:
	Input: cost = [10, 15, 20]
	Output: 15
	Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
	Example 2:
	Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
	Output: 6
	Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
	Note:
	cost will have a length in the range [2, 1000].
	Every cost[i] will be an integer in the range [0, 999].
 */

// Solution 4: Bottom-up DP with two variables
// Time: O(n)
// Space: O(1)
// 12/16/2017
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int first = cost[0];
        int second = cost[1];
        for (int i = 2; i <= cost.length; i++) {
            int c = i < cost.length ? cost[i] : 0;
            int res = c + Math.min(first, second);
            first = second;
            second = res;
        }
        return second;
    }
}

// Solution 3: Bottom-up DP
// Start from the bottom, current result depends on previous two results.
// Time: O(n)
// Space: O(n)
// 12/16/2017
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length+1];
        memo[0] = cost[0];
        memo[1] = cost[1];
        for (int i = 2; i <= cost.length; i++) {
            int c = i < cost.length ? cost[i] : 0;
            memo[i] = Math.min(memo[i-1], memo[i-2]) + c;
        }
        return memo[cost.length];
    }
}

// Solution 2: Top-down recursion with memoization
// Time: O(n)
// Space: O(n)
// 12/16/2017
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length+1];
        Arrays.fill(memo, -1);
        return helper(cost, memo, cost.length);
    }
    
    public int helper(int[] cost, int[] memo, int i) {
        if (i == 0) {
            return cost[0];
        } else if (i == 1) {
            return cost[1];
        } else if (memo[i] != -1) {
            return memo[i];
        } else {
            int c = i == cost.length ? 0 : cost[i];
            memo[i] = c + Math.min(helper(cost, memo, i-1), helper(cost, memo, i-2));
            return memo[i];
        }
    }
}

// Solution 1: Recursion without memoization
// Time Limit Exceeded
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        return helper(cost, cost.length);
    }
    
    public int helper(int[] cost, int i) {
        if (i == 0) {
            return cost[0];
        } else if (i == 1) {
            return cost[1];
        } else {
            int c = i == cost.length ? 0 : cost[i];
            return c + Math.min(helper(cost, i-1), helper(cost, i-2));
        }
    }
}