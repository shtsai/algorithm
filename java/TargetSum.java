/*
    You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

    Find out how many ways to assign symbols to make sum of integers equal to target S.

    Example 1:
    Input: nums is [1, 1, 1, 1, 1], S is 3. 
    Output: 5
    Explanation: 

    -1+1+1+1+1 = 3
    +1-1+1+1+1 = 3
    +1+1-1+1+1 = 3
    +1+1+1-1+1 = 3
    +1+1+1+1-1 = 3

    There are 5 ways to assign symbols to make the sum of nums be target 3.
    Note:
    The length of the given array is positive and will not exceed 20.
    The sum of elements in the given array will not exceed 1000.
    Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

// Solution 3: Dynamic Programming with 2D array
// Build 2D DP array.
// Each cell dp[i][j] represents the number of possible combinations.
// i means that we uses all numbers from index 0 to index i (inclusively).
// j is the sum we have.
// The recurrence relation is as follow:
// If dp[i-1][j] > 0, then with one more number, we can reach a somewhere else
// by adding or subtracting nums[i].
// So dp[i][j+nums[i]] and dp[i][j-nums[i]] can be reached, and we add
// the value of dp[i-1][j] to them.
// Repeat this process, and our final result will be stored in
// dp[nums.length-1][S], which is the number of possible combinations
// using all numbers that have a sum of S.
// Note that the range of the sum is [-1000, 1000],
// so we need to add 1000 to the index.
//
// Time: O(ln) - l is the range of the sum, n is the length of the array
// Space: O(ln)
// 11/07/2017
 
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;   // need "+=" b/c nums[0] might = -nums[0]
        
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i-1][sum + 1000] > 0) {     // exists
                    dp[i][sum + nums[i] + 1000] += dp[i-1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i-1][sum + 1000];
                }
            }
        }
        
        return S > 1000 || S < -1000 ? 0 : dp[nums.length-1][S+1000];
    }
}

// Solution 2: DFS with memoization
// Use a memo array to store the result calculated earlier.
// Index of the array with be current index + current sum we have.
// The value of each cell represents the # of valid combinations given
// the current index and sum.
// Note that because we know the range of sum is [-1000, 1000],
// so we need a 2D array of size (nums.length * 2001).
// Also note the indexing.
//
// Time: O(ln) - l is the range of sum, n the length of array
// Space: O(n) 
// 11/07/2017

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];   // possible range [-1000, 1000]
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return finder(nums, S, memo, 0, 0);
    }
    private int finder(int[] nums, int S, int[][] memo, int index, int current) {
        if (index == nums.length) {
            if (S == current) {
                return 1;       // find a valid combination
            } else {
                return 0;
            }
        } else if (memo[index][current+1000] != -1) {
            return memo[index][current+1000];
        } else {
            int plus = finder(nums, S, memo, index+1, current + nums[index]);
            int minus = finder(nums, S, memo, index+1, current - nums[index]);    
            memo[index][current+1000] = plus + minus;
            return memo[index][current+1000];
        }
        
    }
}

// Solution 1: DFS (Brute force)
// At each index, we have two options, add or minus current number.
// DFS all possible combinations, and increment count when we reach sum of target.
// Time: O(2^n)
// Space: O(n) - call stack
// 11/07/2017

class Solution {
    int count;
    public int findTargetSumWays(int[] nums, int S) {
        count = 0;
        finder(nums, S, 0, 0);
        return count;
    }
    
    private void finder(int[] nums, int S, int index, int current) {
        if (index == nums.length) {
            if (S == current) count++; 
            return;
        }
        
        finder(nums, S, index+1, current + nums[index]);
        finder(nums, S, index+1, current - nums[index]);
    }
}