/*
	Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

	For example, given the array [2,3,1,2,4,3] and s = 7,
	the subarray [4,3] has the minimal length under the problem constraint.

	More practice:
	If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

	Credits:
	Special thanks to @Freezen for adding this problem and creating all test cases.
 */

// Solution 1:
// Two pointers, maintain a sliding window sum
// Time: O(n)
// Space: O(1)
// 09/11/2018
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0, sum = 0, min = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (left < right && sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

// Solution 2: Binary search
// First build cumulative sum array, then do binary search.
// Time: O(nlogn)
// Space: O(n)
// 04/25/2018
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }        
        int[] presum = new int[nums.length + 1];
        for (int i = 1; i < presum.length; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int start = binarySearch(presum, 0, i, presum[i + 1] - s);
            if (start != -1) {
                
                min = Math.min(min, i + 1 - start);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    // Find last index that has a value smaller than target
    public int binarySearch(int[] presum, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (presum[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (presum[right] <= target) {
            return right;
        } else if (presum[left] <= target) {
            return left;            
        } else {
            return -1;
        }
    }
}