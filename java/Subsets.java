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

// Solution 2:
// iterative through each integer in the array
// two possible choices: add it to the set, or not to add it
// recursively making such choices and add to the result when we have gone over all integers
// time: O(2^n)
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


// Solution 1: version 2
// backtracking
// time: O(n!)
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

// Solution 1: version 1
// need to add empty subset manually
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());     // empty subset
        helper(nums, res, 0, new ArrayList<Integer>());
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, int index, List<Integer> list) {
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            res.add(new ArrayList<Integer>(list));
            helper(nums, res, i+1, list);
            list.remove(list.size()-1);
        }
    }
}
