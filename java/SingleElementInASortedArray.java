/*
	Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

	Example 1:
	Input: [1,1,2,3,3,4,4,8,8]
	Output: 2
	Example 2:
	Input: [3,3,7,7,10,11,11]
	Output: 10
	Note: Your solution should run in O(log n) time and O(1) space.
 */

// Solution 2: 
// version 2: more consice
// Single number can only occur on even index,
// So we only check numbers on even index.
// If nums[mid] != nums[mid + 1], then nums[mid] is the single.
// Otherwise, nums[mid] and nums[mid+1] is a duplicate pairs,
// we continue search to its right (left = mid + 2)
// 11/27/2017
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {     // make sure mid is always even
                mid--;
            }
            if (nums[mid] != nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 2;     // continue searching to the right
            }
        }
        return nums[left];
    }
}

// Solution 2: Binary Search
// Observe: suppose single number at index i,
// For all duplicate pairs (a, b) before index i, a/2 == b/2.
// For all duplicate pairs (a', b') after index i, a'/2 < b'/2.
//
// Therefore, at each mid, there are three possibilities:
//		1. nums[mid] == nums[mid-1] 
//			a. If mid / 2 == (mid - 1) / 2, mid is to the left of single
//			b. else, mid is to the right of single
//		2. nums[mid] == nums[mid+1]
//			a. If mid / 2 == (mid + 1) / 2, mid is to the left of single
//			b. else, mid is to the right of single
//		3. mid is different from the left and right, mid is the single number
//
// Time: O(lgn)
// Space: O(1)
// 11/27/2017

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid - 1 >= 0 && nums[mid] == nums[mid-1]) {
                if (((mid - 1) / 2) == (mid / 2)) {
                    System.out.println("here");
                    left = mid + 1;
                } else {
                    right =  mid - 1;
                }
            } else if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                if (((mid + 1) / 2) == (mid / 2)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}

// Solution 1: XOR
// XOR all numbers to cancel out duplicate number,
// and the final result will be the single number.
// Time: O(n)
// Space: O(1) 
// 11/27/2017
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }
}