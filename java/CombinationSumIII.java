/*
	Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

	Note:

	All numbers will be positive integers.
	The solution set must not contain duplicate combinations.
	Example 1:

	Input: k = 3, n = 7
	Output: [[1,2,4]]
	Example 2:

	Input: k = 3, n = 9
	Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

// Solution 1: Backtracking
// Similar to CombinationSum I and II
// Time: O(C(n, k))
// Space: O(n)
// 08/30/2018

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
    	// create an array containing [0, 9]
        int[] nums = new int[9];	
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, k, n, 0, new ArrayList<>());
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, int k, int target, int index, List<Integer> current) {
        if (target == 0 && k == 0) {
            res.add(new ArrayList<>(current));
            return;
        } else if (k <= 0 || target < 0) {
            return;
        } else {
            for (int i = index; i < nums.length; i++) {
                current.add(nums[i]);
                backtrack(nums, res, k - 1, target - nums[i], i + 1, current);
                current.remove(current.size() - 1);
            }
        }
    }
}