/*
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 */

// Solution 1: Four pointers
// Sort the array in ascending order
// Use two pointers to point at first and second number.
// The remaining two pointers do two sum operations to find target.
// Need to handle duplicate numbers.
// Time: O(n^3) - two outer loop, and one O(n) two sum search
// Space: O(1)
// 10/03/2017

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;   // skip duplicates
            for (int j = i + 1; j < nums.length -2; j++){  // convert into 3 Sum
                if (j > i + 1 && nums[j] == nums[j-1]) continue;   // skip duplicates
                int k = j + 1;
                int l = nums.length - 1;
                
                while (k < l) {  // convert into 2 Sum
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        while(k < l && nums[k]==nums[k+1]) k++;  // skip duplicates
                        while(k < l && nums[l]==nums[l-1]) l--;  // skip duplicates
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
                
            }
        }
        return result;
    }
}