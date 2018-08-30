/*
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 */

// Solution 3: Three reverses
// first reverse the whole array
// then the first half up to k
// lastly, reverse the rest of the array 
// Time: O(n)
// Space: O(1)

public class Solution {
    public void rotate(int[] nums, int k) {
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
}

// Solution 2: 
// Rotate the array in a temp array
// Time: O(n)
// Space: O(n)
// 08/30/2018
class Solution {
    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % nums.length] = nums[i];
        }
        
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
}

// Solution 1: Store and rotate
// Store and rotate
// Time: O(n) 
// Space: O(k) 
// store the last k elements, and then rotate
class Solution {
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
}