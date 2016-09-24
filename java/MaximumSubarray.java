/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 */

public class Solution {
	public int maxSubArray(int[] nums){
		
		// dynamic programming
		int len = nums.length;
		int max = nums[0];
		int currentSum = nums[0];
		for (int i = 1; i < len; i++) {
			currentSum = Math.max(currentSum + nums[i], nums[i]);
			if (currentSum > max) {
				max = currentSum;
			}
		}
		return max;

		/*
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
		*/
	}
}