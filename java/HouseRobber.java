/*
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, the only constraint 
 * stopping you from robbing each of them is that adjacent houses have 
 * security system connected and it will automatically contact the police 
 * if two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */

// Solution 1 version 2:
// Since we only need to refer to the previous two days, 
// there is no need to maintain a long array of size N.
// We can just use two variables to keep track of the two
// previous states.
// Time: O(n)
// Space: O(1)
// 11/04/2017

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int two = nums[0];
        int one = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            int max = Math.max(one, two + nums[i]);
            two = one;
            one = max;
        }
        return one;
    }
}

// Solution 1: Dynamic programming
// Each cell i in DP array respresents the maximum result ending with a robbery in houese i.
// Therefore, at each house i, we have two choices:
//      1. To rob i, and get best result from dp[i-2]
//      2. Not to rob i, and we get same result from dp[i-1]
//
// Time: O(n) - one-pass
// Space: O(1)

public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int[] OPT = new int[nums.length];   // each entry in OPT represents the optimal result if the robbery ends here
        OPT[0] = nums[0];                   // initialize first house
        OPT[1] = Math.max(nums[1], OPT[0]); // initialize second house
        
        for (int i = 2; i < nums.length; i++) {
            OPT[i] = Math.max(nums[i] + OPT[i-2], OPT[i-1]); // at each house, decide to rob or not to rob
        }
        return OPT[nums.length-1];
    }
}