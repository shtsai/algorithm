/*
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

// Solution 3:
// Optimal, one pass solution
// Use a pointer points at the first zero element, then swap non zero element
// to there.
// Time: O(n) - one pass
// Space: O(1)
// 08/23/2018

class Solution {
    public void moveZeroes(int[] nums) {
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, zero);
                zero++;
            }
        }
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

// Solution 2:
// Two pointer, one scans through the array,
// the other points to the insert position.
// Move all non-zero numbers to the front, then fill the rest with 0s.
// Time: O(n) - two passes
// Space: O(1)
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        int insert = 0;  // start insert position from 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {  // if the current number is not zero, move it to the insert position
                nums[insert] = nums[i];
                insert++; // move the insert position to right by 1
            }
        }
        
        for (int i = insert; i < nums.length; i++) {  // fill the remaining will zeroes
            nums[i] = 0;
        }
    }
}

// Solution 1:
// Two pointers, one points at zero value, the other points at nonzero value.
// Scan through the array, swap zero value with nonzero values
// Time: O(n^2) worst case
// Space: O(1)
// 10/01/2017
class Solution {
    public void moveZeroes(int[] nums) {
        int zero = 0, nonzero = 0;
        while (zero < nums.length) {
            while (zero < nums.length && nums[zero] != 0) zero++;
            nonzero = zero;
            while (nonzero < nums.length && nums[nonzero] == 0) nonzero++;
            if (zero >= nums.length || nonzero >= nums.length) break;
            int temp = nums[zero];
            nums[zero] = nums[nonzero];
            nums[nonzero] = temp;
        }
    }
}

