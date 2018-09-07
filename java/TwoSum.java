/*
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *  
 */

// Solution 1:
// use HashMap to store nums for fast look up
// Time: O(n)
// Space: O(n)
// 09/07/2018
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                res[0] = i;
                res[1] = map.get(target-nums[i]);
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
