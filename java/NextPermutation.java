/*
    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

    The replacement must be in-place, do not allocate extra memory.

    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
 */

// reference: http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
// reference: https://leetcode.com/articles/next-permutation/

// Solution 1 version 2:
// First scan from right to left, find first number i that is smaller than its right neighbor.
// Then scan from right to left again, find the smallest number j that is greater than i.
// Swap i and j, and reverse the rest of the array.
// Time: O(n) - two pass
// Space: O(1)
// 10/21/2017

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int len = nums.length;
        int i = len-2;
        while (i >= 0 && nums[i] >= nums[i+1]) { 
            i--;    // i is at the first decreasing position (from right to left)
        }
        
        if (i >= 0) {
            int j = i+1;
            while (j < len && nums[j] > nums[i]) {
                j++;    // nums[j-1] is the smallest number that is great than nums[i]
            }
            swap(nums, i, j-1);
        } // else the whole array is in descending order
        reverse(nums,i+1, len-1);

    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}

// Solution 1:
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // find the position that we need to increase
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i+1]) {
                
                // find the next larger int
                int j = i + 1;
                for (; j < nums.length; j++) {
                    if (nums[j] <= nums[i]) {
                        break;
                    }
                }
                j--;
        
                // swap i and j
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
        
                // reverse the subarray starting from i+1
                reverse(nums, i+1);
                return;
                }
            i--;
        }
        
        reverse(nums, 0);            
        return;
    }
    
    public void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
}