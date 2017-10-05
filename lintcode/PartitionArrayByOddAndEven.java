/*
    Partition an integers array into odd number first and even number second.
    
    Example:
    Given [1, 2, 3, 4], return [1, 3, 2, 4]
 */

// Solution 1: two pointers
// Use a pointer to find even numbers from the left,
// and use a pointer to find odd numbers from the right.
// Swap the two numbers so that they are in the correct section.
// Repeat until two pointers intersect.
// Time: O(n) - one pass
// Space: O(1)
// 10/05/2017

public class Solution {
    /*
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int even = 0, odd = nums.length-1;
        while (true) {
            while (even < nums.length && nums[even]%2==1) even++;
            while (odd >= 0 && nums[odd]%2==0) odd--;
            if (even < odd) {
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
            } else {
                break;
            }
        }
    }
}