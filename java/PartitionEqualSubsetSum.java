/*
	Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

	Note:
	Each of the array element will not exceed 100.
	The array size will not exceed 200.

	Example 1:
	Input: [1, 5, 11, 5]
	Output: true
	Explanation: The array can be partitioned as [1, 5, 5] and [11].
	
	Example 2:
	Input: [1, 2, 3, 5]
	Output: false
	Explanation: The array cannot be partitioned into equal sum subsets.
 */

// Solution 4: Buttom-up DP, Space optimized
// DP[i][j] only depends on the previous row
// So we only need an array of size n
// Note that we iterate target from large to small values.
// 
// Time: O(kn)
// Space: O(n)
// 09/22/2018
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = sum / 2; j > 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] |= dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum / 2];
    }
}

// Solution 3: Buttom-up DP
// Time: O(kn)
// Space: O(kn)
// 09/22/2018
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];
        for (int i = 0; i < nums.length + 1; i++) { 
            dp[i][0] = true;    // base case
        }
        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                dp[i][j] = dp[i - 1][j];    // not pick
                if (j - nums[i - 1] >= 0) {   // pick
                    dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum / 2];
    }
}

// Solution 2: 2D memoization, Knapsack\
// Store the search result for each index and target
// Time: O(kn)
// Space: O(kn)
// 09/22/2018
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int[][] memo = new int[nums.length][sum / 2 + 1];
        int max = search(nums, sum / 2, 0, memo);
        return max == sum / 2;
    }
    
    private int search(int[] nums, int target, int index, int[][] memo) {
        if (index == nums.length) {
            return 0;
        } else if (memo[index][target] != 0) {
            return memo[index][target];
        }
        int take = 0;
        if (nums[index] <= target) {
            take = search(nums, target - nums[index], index + 1, memo) + nums[index];
        }
        int noTake = search(nums, target, index + 1, memo);
        memo[index][target] = Math.max(take, noTake);
        return memo[index][target];
    }
}

// Solution 1: Backtracking
// Since all numbers are positive, we can mark a number to negative to indicate it has been taken.
// Time: O(n * n!)
// Space: O(n) - call stack
// 09/22/2018

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        return search(nums, sum / 2);
    }
    
    private boolean search(int[] nums, int target) {
        if (target == 0) {
            return true;
        } else if (target < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                continue;
            }
            nums[i] *= -1;
            if (search(nums, target + nums[i])) {
                nums[i] *= -1;
                return true;
            }
            nums[i] *= -1;
        }
        return false;
    }
}