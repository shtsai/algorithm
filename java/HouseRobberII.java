/*
 * After robbing those houses on that street, the thief has found himself a new 
 * place for his thievery so that he will not get too much attention. 
 * This time, all houses at this place are arranged in a circle. 
 * That means the first house is the neighbor of the last one. 
 * Meanwhile, the security system for these houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house, 
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */

// Solution 1 version 2:
// Since the houses now are placed in a circular manner,
// we need to perform two dp operations, one including the 
// first house and excluding the last house, and one including
// the last house and excluding the first house.
// This will give us the optimal result.
//
// Time: O(n) - two passes
// Space: O(1) - two varibles
// 11/04/2017

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int two = nums[0];
        int one = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length-1; i++) {   // rob first house
            int max = Math.max(one, two + nums[i]);
            two = one;
            one = max;
        }
        int res = one;
        
        two = 0;
        one = nums[1];
        for (int i = 2; i < nums.length; i++) {     // don't rob first house
            int max = Math.max(one, two + nums[i]);
            two = one;
            one = max;
        }
        return Math.max(res, one);
    }
}

// Solution 1 version 1:
// Time: O(n) - two passes
// Space: O(n) - dp array
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int len = nums.length;
        int[] OPT = new int[len];
        
        OPT[0] = nums[0];
        OPT[1] = Math.max(OPT[0], nums[1]);
        for (int i = 2; i < len-1; i++) {   // intentionally skip the last house
            OPT[i] = Math.max(OPT[i-2]+nums[i], OPT[i-1]);
        }
        int temp = OPT[len-2];
        
        OPT[0] = 0;
        OPT[1] = nums[1];
        for (int i = 2; i < len; i++) { // intentionally skip the first house
            OPT[i] = Math.max(OPT[i-2]+nums[i], OPT[i-1]);
        }
        
        return Math.max(temp, OPT[len-1]);
    }
}