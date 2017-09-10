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

class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[left] < nums[mid] || nums[mid] > nums[right]) {    // left is sorted or right is unsorted
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[right] || nums[mid] < nums[left]) {   // right is sorted or left is unsorted
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {      // nums[left] == nums[mid] == nums[right]
                left++;   // move left to remove one duplicate
            }
        }
        return nums[left] == target ? true : false;

    }
}
