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

// Solution 1: version 2
// more consice
// farest stands for the farest distance reachable
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        int farest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (farest >= i) {
                farest = Math.max(farest, nums[i] + i);
            } else {
                return false;
            }
        }
        return farest >= nums.length-1;
    }
}

// Solution 1: greedy
// verison 1:
// use a variable reach to keep track of the max position reachable
public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        
        int reach = nums[0];
        for (int i = 1; i < nums.length-1 && reach >= i; i++) {   // need reach >= i, otherwise cannot reach i
            reach = Math.max(reach, i + nums[i]);   // update reach
            if (reach >= nums.length-1) return true;
        }
        if (reach < nums.length-1) return false;
        else return true;
    }
}