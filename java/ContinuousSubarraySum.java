/*
	Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

	Example 1:
	Input: [23, 2, 4, 6, 7],  k=6
	Output: True
	Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
	
	Example 2:
	Input: [23, 2, 6, 4, 7],  k=6
	Output: True
	Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
	
	Note:
	The length of the array won't exceed 10,000.
	You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

// Solution 2: Mod and HashMap
// Store (mod, index) in a hash map
// As we accumulate sum, we also mod by k.
// If the current mod is in the map, that means there is a subarray
// that is a multiple of k.
// We then use index to check if the width is at least 2.
// Time: O(n)
// Space: O(n)
// 09/10/2018
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> mods = new HashMap<>();
        mods.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (mods.containsKey(sum)) {
                if (i - mods.get(sum) > 1) {
                    return true;
                }
            } else {
                mods.put(sum, i);
            }
        }
        return false;
    }
}

// Solution 1: PreSum array
// Create a preSum array, and find all subarray sum.
// Check if any is multiple of k.
// Time: O(n ^ 2)
// Space: O(n)
// 09/10/2018
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i + 2; j <= nums.length; j++) {
                int sum = preSum[j] - preSum[i];
                if (k == 0 && sum == 0) {
                    return true;
                } else if (k != 0 && (preSum[j] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}