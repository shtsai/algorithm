/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *
 */

// reference: https://discuss.leetcode.com/topic/28738/java-python-binary-search-o-nlogn-time-with-explanation

public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int n : nums) {
            int i = 0, j = size;
            
            // binary search for correct position given the size
            while (i != j) {
                int mid = i + (j - i) / 2;
                if (tails[mid] < n) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = n;   // insert n into its position
            if (i == size) size++;   // find a num greater than all tails, increment size
        }
        
        return size;
    }
}