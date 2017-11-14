/*
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */

// Solution 2: binary search
// Use binary search, make sure each search range is surrounded by smaller numbers.
// Start from the middle:
// if nums[mid] > nums[mid+1]: [left, mid] must contains a peak, search in that range
// if nums[mid] <= nums[mid+1]: [mid+1, right] must contains a peak, search in that range
//
// Time: O(log(n))
// Space: O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

// Solution 1: Linear scan
// Because index -1 is negative infinity, so for index 0, 
// we only need to check the number on its right.
// If the right number is smaller, then number at index 0 is a peak.
// Otherwise, we continue checking the number on the right.
// Time: O(n)
// Space: O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        // because nums[-1] = -infinity
        // so only need to compare the other side
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                return i;
            }
        }
        // else return the last element
        return nums.length-1;
    }
}

