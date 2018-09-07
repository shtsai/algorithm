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
        int max = 0;
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                }
            }
            max = Math.max(max, res[i]);
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
// 09/07/2018
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(tails, size, nums[i]);
            if (index == size) {
                size++;
            }
            tails[index] = nums[i];
        }
        return size;
    }
    
    // Find insert position for target
    private int binarySearch(int[] nums, int end, int target) {
        int left = 0, right = end;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}