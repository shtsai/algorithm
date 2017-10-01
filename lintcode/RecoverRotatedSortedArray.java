/*
    Given a rotated sorted array, recover it to sorted array in-place.
    
    Clarification
    What is rotated array?

    For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
    Example
    [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
 */

// Solution 1: reverse with two pointers
// First find the index of the min,
// then use that index as a breaking point, reverse the first and second half,
// finally reverse the entire array.
// Time: O(n)
// Space: O(1)
// 10/01/2017

public class Solution {
    /*
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0) return;
        int min = nums.get(0), minIndex = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < min) {
                min = nums.get(i);
                minIndex = i;
            }
        }
        if (minIndex == 0) return;  // already sorted
        reverse(nums, 0, minIndex-1);
        reverse(nums, minIndex, nums.size()-1);
        reverse(nums, 0, nums.size()-1);
    }
    
    private void reverse(List<Integer> nums, int l, int r) {
        while (l < r) {
            int temp = nums.get(l);
            nums.set(l, nums.get(r));
            nums.set(r, temp);
            l++;
            r--;
        }
    }
}