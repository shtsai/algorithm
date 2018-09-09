/*
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Write a function to determine if a given target is in the array.
 *
 * The array may contain duplicates.
 */

// Solution 1:
// modified version of Search in Rotated Sorted Array I
// In this problem, we need to handle duplicates
// Therefore, when nums[left] == nums[mid] == nums[right],
// we can just do left++ to remove it of them so that we can continue searching
// Note that since duplicates exist, it is harder to find the sorted portion of the array.
// Therefore, we need to check if one side is in sorted order, or the other side is unsorted.
//
// Time: O(logn)
// Space: O(1)
// 09/09/2018
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[i] == nums[mid] && nums[mid] == nums[j]) {
                i++;    // move left to remove one duplicate
            } else if (nums[mid] >= nums[i]) {       // left half is in accending order
                if (nums[i] <= target && target <= nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {                                // right half is in accending order
                if (nums[mid] <= target && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                } 
            }
        }
        return nums[i] == target;
    }
}
