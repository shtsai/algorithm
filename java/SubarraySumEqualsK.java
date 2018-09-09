/*
	Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

	Example 1:
	Input:nums = [1,1,1], k = 2
	Output: 2
	
	Note:
	The length of the array is in range [1, 20,000].
	The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

// Solution 2: HashMap
// Use a variable `sum` to accumulate current sum.
// Store all previous sum in a HashMap as <Sum, Occurrence> pair.
// At each iteration, check if (`sum` - `k`) exists.
// If so, then the range from those indices to current index can sum of `k`.
// So we add their count to the result.
//
// Time: O(n)
// Space: O(n)
// 09/09/2018
class Solution {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int n : nums) {
            sum += n;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}	

// Solution 1: PreSum array
// Build preSum array and compute sum for all ranges.
// Time: O(n ^ 2)
// Space: O(n)
// 09/09/2018
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        
        int res = 0;
        for (int i = 0; i < preSum.length; i++) {
            for (int j = 0; j < i; j++) {
                if (preSum[i] - preSum[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}