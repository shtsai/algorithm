/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * Note:
 * You can assume that you can always reach the last index.
 */

// Solution 3:
// greedy solution, similar to BFS solution
// try to go as far as possible, make a jump when reaches the end
// Time: O(n)
// Space: O(1)
// 09/04/2018
class Solution {
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int farthest = 0, end = 0, jump = 1;
        for (int i = 0; i < nums.length-1; i++) {
            farthest = Math.max(farthest, nums[i]+i);    // update farest position can be reached
            if (farthest >= nums.length-1) return jump;   // can reach the destination
            if (i == end) {  // we have reached the end of this jump
                jump++;
                end = farthest;
            }
        }
        return jump;
    }
}

// Solution 2:
// BFS, similiar to the idea of JumpGame I
// at every iteration, we try to get as far as possible,
// then we increase the jump counter and go to next iteration,
// repeat the same process until we reach the goal
// Time: O(n)
class Solution {
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int start = 0, nextFarthest = 0, farthest = 0, jump = 0;
        while (farthest - start >= 0) {
            jump++;
            for (;start <= farthest; start++) {
                nextFarthest = Math.max(nextFarthest, nums[start]+start);
                if (nextFarthest >= nums.length-1) return jump;
            }
            farest = nextFarthest;
        }
        return 0;
    }
}

// Solution 1: 
// Dynamic programming
// use an array to memoize the min steps need to be taken to reach position i
// gradually build the array from left to right
// Time: O(n^2) - Time limit exceeded
// Space: O(n)
class Solution {
    public int jump(int[] nums) {
        int[] minStep = new int[nums.length];   // min step to reach position i
        for (int i = 1; i < nums.length; i++) minStep[i] = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length && j <= i+nums[i]; j++) { 
                // j = # of steps can be taken at this position
                minStep[j] = Math.min(minStep[j], minStep[i]+1);
                if (j == nums.length-1 && minStep[nums.length-1]!=Integer.MAX_VALUE) {
                    return minStep[nums.length-1];
                }
            }
        }
        
        if (minStep[nums.length-1] == Integer.MAX_VALUE) return 0;
        return minStep[nums.length-1];
    }
}