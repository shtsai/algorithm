/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */

// Solution 1: binary search
// Check the middle element, if it is the target, return its index.
// If not, find the sorted half:
//      - If the left half is sorted, check if target is that that range.
//        If so, continue searching on that half, otherwise on the other half
//      - Else, right half is sorted, check if target is that that range.
//
// Note, need to be careful with "=", because left and mid could be pointing at the same number.
// 
// Time: O(log n)
// Space: O(1)
// 10/13/2017

public class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[i]) {       // left half is in accending order
                if (nums[i] <= target && target <= nums[mid]) j = mid - 1;
                else i = mid + 1;
            } else {                               // right half is in accending order
                if (nums[mid] <= target && target <= nums[j]) i = mid + 1;
                else j = mid - 1;
            }
        }
        return nums[i] == target ? i : -1;
    }
}