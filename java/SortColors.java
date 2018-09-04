/*
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */

// Solution 2 version 2:
// zero and two are two pointers that break the array into three parts:
//      0s are located to the left of zero
//      1s are located between zero and two
//      2s are located to the right of two
// Use a pointer i to scan through the array, and assign each number to
// its corresponding section.
// Time: O(n) - one pass
// Space: O(1)
// 10/05/2017

class Solution {
    public void sortColors(int[] nums) {
        int zero = 0, two = nums.length-1, i = 0;
        while (i <= two) {
            if (nums[i] == 0) {
                // b/c the section to the left of i is already sorted,
                // so after swap, we can continue search next index (i++)
                swap(nums, zero, i);
                zero++;
                i++;
            } else if (nums[i] == 1) {  // skip ones
                i++;
            } else { // nums[i] == 2
                // here, b/c sections to the right of i is not yet sorted,
                // so after swap, we need to check the new element at current i
                swap(nums, two, i);
                two--;
            }
        }
    }
    private void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}

// solution 2:
// move 2s to the end, and move 0s to the front
// Time: O(n)
// Space: O(1)
public class Solution {
    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length - 1;
        int index = 0;    
        while (index <= two) {
            while (nums[index] == 2 && index != two) { // swap 2 to the end
                swap(nums, index, two); 
                two--;
            }
            while (nums[index] == 0 && index != zero) { // swap 0 to the front
                swap(nums, index, zero);
                zero++;
            }
            index++;
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// solution 1: Counting sort
// count the frequency of each color, then build the array
// Time: O(n)
// Space: O(1)
public class Solution {
    public void sortColors(int[] nums) {
        int zero = 0, one = 0, two = 0;      
        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]){
                case 0:
                    zero++;
                    break;
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
            }
        }  
        int index = 0;
        for (int i = 0; i < zero; i++) {
            nums[index++] = 0;
        }
        for (int i = 0; i < one; i++) {
            nums[index++] = 1;
        }
        for (int i = 0; i < two; i++) {
            nums[index++] = 2;
        }
    }
}