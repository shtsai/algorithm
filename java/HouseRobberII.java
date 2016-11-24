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