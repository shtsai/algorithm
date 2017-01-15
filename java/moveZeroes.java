/*
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */

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

/*
public class Solution {
    public void moveZeroes(int[] nums) {
        int zero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) { // find first 0
                zero = i;
                break;
            }
        }
        if (zero == -1 || zero == nums.length-1) return; // no zero found
        int count = 1;
        
        for (int i = zero+1; i < nums.length; i++) { // start from the first number after the first zero
            if (nums[i] != 0) {  // if the number is not zero, move it to zero's place and increment zero
                nums[zero] = nums[i];
                zero++;
            } else {  // else increment the count of zeroes
                count++;
            }
        }
        
        for (int i = nums.length-count; i < nums.length; i++) { // overwrite the last few numbers to zero
            nums[i] = 0;
        }
        
        return;
    }
}
*/