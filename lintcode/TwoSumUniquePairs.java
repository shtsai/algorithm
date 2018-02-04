/*
    Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. Please return the number of pairs.

    Example
    Given nums = [1,1,2,45,46,46], target = 47
    return 2

    1 + 46 = 47
    2 + 45 = 47
 */

// Solution 1: Sort + two pointers
// Time: O(nlogn)
// Space: O(1)
// 02/03/2018

public class Solution {
    /*
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] + nums[end] == target) {
                count++;
                start++;
                end--;
                // skip duplicates
                while (start < nums.length && nums[start] == nums[start - 1]) {
                    start++;
                }
                while (end >= 0 && nums[end] == nums[end + 1]) {
                    end--;
                }
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        
        return count;
    }
}