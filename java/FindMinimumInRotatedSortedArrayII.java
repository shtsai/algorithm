/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * Duplicate exists in the array.
 */

public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;

            // here nums[mid] == nums[right], two more possibilities
            } else if (nums[left] < nums[mid]) {  // left side smaller, must be on left side
                right = mid;
            } else {    // otherwise, left is greater, won't be min, skip it
                left = left + 1;
            }
        }
        return nums[left];
    }
}