/*
	Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

	Example:

	nums = [1, 2, 3]
	target = 4

	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)

	Note that different sequences are counted as different combinations.

	Therefore the output is 7.
	Follow up:
	What if negative numbers are allowed in the given array?
	How does it change the problem?
	What limitation we need to add to the question to allow negative numbers?
 */

// Solution 3: Bottom-up DP
// Time: O(kn)
// Space: O(k)
// 08/30/2018
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (i - n >= 0) {
                    memo[i] += memo[i - n];
                }
            }
        }
        return memo[target];
    }   
}

// Solution 2: Recursion + memoization (top down)
// Use a memo array to store intermediate results
// Time: O(k) - k: target
// Space: O(k)
// 08/30/2018
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return helper(nums, memo, target);
    }
    
    private int helper(int[] nums, int[] memo, int target) {
        if (target == 0) {
            return 1;
        } else if (memo[target] != -1) {
            return memo[target];
        } else {
            memo[target] = 0;
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] >= 0) {
                    memo[target] += helper(nums, memo, target - nums[i]);
                }
            }
            return memo[target];
        }
    }   
}

// Solution 1: Recursion
// Break the problem into smaller subproblems 
// 08/30/2018

class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        } else {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] >= 0) {
                    res += combinationSum4(nums, target - nums[i]);
                }
            }
            return res;
        }
    }
}