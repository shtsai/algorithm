/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 */

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