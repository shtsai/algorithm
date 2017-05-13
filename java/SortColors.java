/*
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 */

// solution 2:
// move 2s to the end, and move 0s to the front
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
        
        return;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// solution 1:
// count the frequency of each color, then build the array
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
        
        return;
    }
}