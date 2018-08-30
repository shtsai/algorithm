/*
 * Given a set of distinct integers, nums, return all possible subsets.
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example,
 * If nums = [1,2,3], a solution is:
 *
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 */

// Solution 2: version 2
// backtracking
// Time: O(2^n)
// Space: O(n)
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> list, int start, int[] nums) {
        // base case
        if (start > nums.length) return;

        // add the deep copy of list to the result
        result.add(new ArrayList<Integer>(list));

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            helper(result, list, i+1, nums);
            list.remove(list.size()-1);
        }
        return;
    }
}


// Solution 1:
// Iterate through each integer in the array
// Two possible choices: add it to the set, or not to add it
// Recursively making such choices and add to the result when we have gone over all integers
// Time: O(2^n)
// Space: O(n) - stack space
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    private void helper (int[] nums, int index, List<Integer> list, List<List<Integer>> res) {
        if (index == nums.length) { // we have gone over all integers at this point
            res.add(new ArrayList<Integer>(list));
            return;
        }
        list.add(nums[index]);        // to include this int in the subset
        helper(nums, index+1, list, res);
        list.remove(list.size()-1);   // not to include it
        helper(nums, index+1, list, res);
    }
}
