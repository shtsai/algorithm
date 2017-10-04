/*
    Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

    All elements < k are moved to the left
    All elements >= k are moved to the right
    Return the partitioning index, i.e the first index i nums[i] >= k.
 */

// Solution 1: Two pointers
// Use one pointer to scan from the left, find the number >= k,
// and use the other pointer from the right, find the number < k,
// and then swap the two numbers.
// Repeat this process until two pointers intersect.
// Left should point at the first number >= k.
// Time: O(n)
// Space: O(1)
// 10/04/2017

public class Solution {
    /*
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            while (left <= right && nums[left] < k) left++;
            while (left <= right && nums[right] >= k) right--;
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}