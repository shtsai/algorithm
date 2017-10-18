/*
    Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

    Example 1:
    Input: [1,3,5,4,7]
    Output: 3
    Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
    Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
    Example 2:
    Input: [2,2,2,2,2]
    Output: 1
    Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
    Note: Length of the array will not exceed 10,000.
 */

// Solution 2: One pointer
// Use one pointer to scan through the array.
// Maintain a overall max, and the length of LCIS ending at this index.
// When scanning, if current value is greater than the previous one,
// increase the current length and update max. Otherwise, reset current
// length = 1;
//
// Time: O(n) - one pass
// Space: O(1)
// 10/18/2017

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int cur = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i-1] < nums[i]) {
                cur++;
                max = Math.max(max, cur);
            } else {
                cur = 1;
            }
        }
        return max;
    }
}

// Solution 1: Two pointer
// Use two pointers to find windows of continuous increasing subsequence.
// When the window reaches its maximum, update the max.
// Set start to end + 1, and continue searching.
//
// Time: O(n) - one pass
// Space: O(1)
// 10/18/2017

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int start = 0, end = 0, max = 0;
        while (end < nums.length && start < nums.length) {
            while (end+1 < nums.length && nums[end+1] > nums[end]) {
                end++;      // find all continuous increasing subsequence
            }
            max = Math.max(max, end-start+1);
            start = end + 1;
            end = start;
        }
        return max;
    }
}