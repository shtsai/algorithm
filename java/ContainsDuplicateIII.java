/*
    Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

    Example 1:

    Input: nums = [1,2,3,1], k = 3, t = 0
    Output: true
    Example 2:

    Input: nums = [1,0,1,1], k = 1, t = 2
    Output: true
    Example 3:

    Input: nums = [1,5,9,1,5,9], k = 2, t = 3
    Output: false
 */

// Solution 2: Binary search tree
// Use floor() and ceiling() of TreeSet to find max and min in the current window.
// Time: O(nlogk) - floor(), ceiling(), remove() are O(logk)
// Space: O(k)
// 07/04/2018
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> bst = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long min = bst.floor((long) nums[i]);
            if (min != null && nums[i] - min <= t) {
                return true;
            }
            Long max = bst.ceiling((long) nums[i]);
            if (max != null && max - nums[i] <= t) {
                return true;
            }

            bst.add((long) nums[i]);
            if (bst.size() > k) {
                bst.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}

// Solution 1: Brute force with for loop
// Cast to long to prevent overfloat
// Time: O(nk)
// Space: O(1)
// 07/04/2018
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                long res = Math.abs((long) nums[i] - nums[j]);
                if (res <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
