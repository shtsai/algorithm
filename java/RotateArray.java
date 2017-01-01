/*
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 */

public class Solution {

    // Three reverse calls
    // O(n) time, O(1) space
    public void rotate(int[] nums, int k) {
        // first reverse the whole array
        // then the first half up to k
        // lastly, reverse the rest of the array 
        int len = nums.length;
        k = k % len;
        reverse(nums, 0, len-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, len-1);
    }
    
    // helper function to reverse an array from index i to index j (inclusive)
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            j--;
            i++;
        }
    }

    /*  
    // Store and rotate
    // O(n) time, O(k) space
    // store the last k elements, and then rotate
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int[] temp = new int[k];
        
        for (int i = 0; i < k; i++) {
            temp[i] = nums[len-k+i];
        }
        for (int i = len-k-1; i >= 0; i--) {
            nums[i+k] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
    */
}
