/*
	Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

	Your algorithm's runtime complexity must be in the order of O(log n).

	If the target is not found in the array, return [-1, -1].

	Example 1:
	Input: nums = [5,7,7,8,8,10], target = 8
	Output: [3,4]
	
	Example 2:
	Input: nums = [5,7,7,8,8,10], target = 6
	Output: [-1,-1]
 */

// Solution 2: Binary search for insert position
// Time: O(logn)
// Space: O(1)
// 09/11/2018
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = insertPosition(nums, target);
        res[1] = insertPosition(nums, target + 1) - 1;
        if (res[0] < 0 || res[0] >= nums.length || nums[res[0]] != target ||
            res[1] < 0 || res[1] >= nums.length || nums[res[1]] != target) {
            return new int[] {-1, -1};
        }
        return res;
    }
    
    private int insertPosition(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

// Solution 1: Two Binary search
// Time: O(logn)
// Space: O(1)
// 08/30/2018

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        int[] res = new int[2];
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }
    
    private int searchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else {
            return -1;
        }
    }
    
    private int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) {
            return right;
        } else if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}