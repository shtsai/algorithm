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

//Dynamic programming
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