/*
 * Given an array of 2n integers, 
 * your task is to group these integers into n pairs of integer, 
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 */

// first sort the array, then sum up 1st, 3rd, 5th ... numbers
// uses pre-built sort function
public class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i+=2) {
            result += nums[i];
        }
        return result;
    }
}

// self-implemented merge sort (poor performance)
public class Solution {
    public int arrayPairSum(int[] nums) {
        mergeSort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i+=2) {
            result += nums[i];
        }
        return result;
    }
    
    public void mergeSort(int[] nums) {
        divide(nums, 0, nums.length - 1);
    }
    
    public void divide(int[] nums, int start, int end) {
        if (end > start) {
            divide(nums, start, (end+start)/2);
            divide(nums, (end+start)/2 + 1, end);
            merge(nums, start, (end+start)/2, end);
        }
    }
    
    public void merge(int[] nums, int start, int mid, int end) {
        int[] left = new int[mid-start+1];
        int[] right = new int[end-mid];
        
        for (int i = start; i <= mid; i++) {
            left[i-start] = nums[i];
        }
        for (int i = mid+1; i <= end; i++) {
            right[i-mid-1] = nums[i];
        }
        
        int i = start, j = mid+1, k = start;
        while (i <= mid && j <= end) {
            if (left[i-start] <= right[j-mid-1]) {
                nums[k] = left[i-start];
                i++;
            } else {
                nums[k] = right[j-mid-1];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            nums[k] = left[i-start];
            i++;
            k++;
        }
    }
}