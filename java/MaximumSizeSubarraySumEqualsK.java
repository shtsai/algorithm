/*
    Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

    Note:
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

    Example 1:
    Given nums = [1, -1, 5, -2, 3], k = 3,
    return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

    Example 2:
    Given nums = [-2, -1, 2, 1], k = 1,
    return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

    Follow Up:
    Can you do it in O(n) time?
 */

// Solution 2:
// Use a HashMap to store the sum between [0: i].
// The key of HashMap will be the subarray sum and value is the start index.
// Then at each index i, we can search the HashMap to see if (sum[0:i]-k) exists.
// If so, we find a subarray sum to k. Update max.
// Note that we are looking for maximum length, so if there are multiple
// subarrays sum to the same value, we only store the index of the first one.
//
// Time: O(n)
// Space: O(n) - HashMap
// 11/19/2017
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {    // only store first occurence (left most)
                map.put(sum, i);
            }
        }
        return max;
    }
}

// Solution 1: Brute force
// Try all n^2 subarray
// Time: O(n^2)
// Space: O(1)
// 08/23/2018

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int res = 0;
            for (int j = i; j < nums.length; j++) {
                res += nums[j];
                if (res == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
