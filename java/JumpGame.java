/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 */

// greedy
// use a variable reach to keep track of the max position reachable
public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int reach = nums[0];
        for (int i = 1; i < nums.length-1 && reach >= i; i++) {   // need reach >= i, otherwise cannot reach i
            reach = Math.max(reach, i + nums[i]);   // update reach
            if (reach >= nums.length-1) return true;
        }
        if (reach < nums.length-1) return false;
        else return true;
    }
}