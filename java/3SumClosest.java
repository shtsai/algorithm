/*
 * Given an array S of n integers, 
 * find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. 
 * You may assume that each input would have exactly one solution.
 */

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // similar to 3Sum, convert the problem into 2Sum

        int delta = Integer.MAX_VALUE;
        int sum = 0;
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            int smallTarget = target - nums[i];
            while (j < k) {
                int temp = nums[i] + nums[j] + nums[k];
                if (temp == target) return temp;
                
                if (delta > Math.abs(temp-target)) {
                    sum = nums[i]+nums[j]+nums[k];
                    delta = Math.abs(temp-target);
                }
                if (temp > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return sum;
    }
}