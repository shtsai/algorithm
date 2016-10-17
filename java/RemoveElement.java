/*
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class Solution {
    public int removeElement(int[] nums, int val) {
        // use index to count the number of valid element and their indices
        int index = 0;
        
        // iterate through the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {      // when find valid element, 
                nums[index] = nums[i]; // add to the array
                index++; /             // and increment index pointer
            }                          // don't need to worry about index > i,
        }                              // because index grows at most at the growth speed of i
        return index;
    }
}