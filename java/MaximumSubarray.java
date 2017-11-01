/*
	Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

	For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */

// Solution 3: Dynamic Programming
// Use two variables:
//		max: the global maximum
// 		currentSum: an accumulator for the sum
// Scan through the array, for each number, we have two choices.
// 		1. add to the current sum
//		2. forget about previous sum, start a new sum containing only this number
// We choose the larger result of the two and update global maximum.
//
// Time: O(n)
// Space: O(1)
// 11/01/2017

class Solution {
	public int maxSubArray(int[] nums){
		int max = nums[0];
		int currentSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currentSum = Math.max(currentSum + nums[i], nums[i]);
			max = Math.max(max, currentSum);
		}
		return max;
	}
}

// Solution 2: Dynamic Programming with DP array
// Use a DP array to maintain the state at each step.
// Each cell i of the array represents the continuou sum ending with nums[i].
// Therefore, at each iteration, we need to check previous sum, and see if 
// it is greater than 0. 
// If it is, then we can append nums[i] and increment sum by nums[i].
// If not, then including previous will only result in a smaller sum,
// so we can start a new subarray with only nums[i].
//
// Time: O(n)
// Space: O(n)
// reference: https://discuss.leetcode.com/topic/6413/dp-solution-some-thoughts

class Solution {
	public int maxSubArray(int[] nums){
        int[] dp = new int[nums.length];
		int max = nums[0];
		dp[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = (dp[i-1] > 0 ? dp[i-1] : 0) + nums[i];
            max = Math.max(max, dp[i]);
		}
		return max;
	}
}


// Solution 1: Brute Force
// Time: O(n^3)
// Space: O(1)
public class Solution {
	public int maxSubArray(int[] nums){
		//brute force
		int len = nums.length;
		int max = Integer.MIN_VALUE;
		for (int start = 0; start < len; start++) {
			for (int end = start + 1; end < len+1; end++) {
				int sum = 0;
				// sum up all the values from start to end
				for (int i = start; i < end; i++){
					sum += nums[i];
				}
				if (sum > max) {max = sum;}
			}
		}
		return max;
	}
}