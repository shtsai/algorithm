/*
    Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

    Example 1:
    Input: [0,1]
    Output: 2
    Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
    Example 2:
    Input: [0,1,0]
    Output: 2
    Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
    Note: The length of the given binary array will not exceed 50,000.
 */

// Solution 1 version 2:
// Same idea, without modifying original array
// Time: O(n)
// Space: O(n)
// reference: https://leetcode.com/articles/contiguous-array/
// 09/27/2017

class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count--;
            } else {
                count++;
            }
            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);  // only store first occurance
            }
        }
        return max;
    }
}

// Solution 1:
// change all '0' to '-1' and compute the cumulative sum of the array.
// A subarray of sum 0 contains equal number of '0' and '1'
// Therefore, when we get a new sum, we just need to check if there exists a sum
// that equals to current sum. If so, subtract to subarrays will give us a continuous
// array with equal number of 0s and 1s.
// Time: O(n)
// Space: O(n)
// reference: https://discuss.leetcode.com/topic/79906/easy-java-o-n-solution-presum-hashmap
// 09/27/2017

class Solution {
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>(); // stores mapping from sum to ending index
        map.put(0, -1);
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i); // only store the first occurance of the sum (in order to find maximum)
            }
        }
        return max;
    }
}