/*
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 *
 */

// Solution 2: Dynamic Programming
// Use an array to store the length of LIS ending at the current index
// Scan through the array, for each number n, find max value among its predecessors that are smaller than n
//
// Time: O(n^2)
// Space: O(n)
// 09/27/2017

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int pre = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    pre = Math.max(pre, dp[j]);
                }
            }
            dp[i] = pre + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

// Solution 1:
// Use a array to maintain the tails of LIS of various lengths
// For example:
//      tails[0] contains tail for LIS of size 1
//      tails[2] contains tail for LIS of size 3
// Given a new number n, use binary search to find its correct insert position i.
// If i already exists in tails array, that means n is smaller and thus can replace that number
// If i is greater than the size of tails, that means n can be append to previous LIS,
// so we increase size of tails, and put n there.
//
// Time: O(nlogn)
// Space: O(n)
// reference: https://discuss.leetcode.com/topic/28738/java-python-binary-search-o-nlogn-time-with-explanation
// 09/27/2017

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