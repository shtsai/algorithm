/*
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * Note:
 *
 *    All numbers (including target) will be positive integers.
 *    The solution set must not contain duplicate combinations.
 *
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 *
 * [
 *  [7],
 *  [2, 2, 3]
 * ]
 */

// Solution 1:
// Recursion and backtracking
// Time: O(2^n)
// Space: O(n) - call stack

// version 2:
// 01/05/2018
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        search(candidates, target, 0, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void search(int[] candidates, int target, int current, int index, List<Integer> list, List<List<Integer>> res) {
        if (current > target) return;
        if (current == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            search(candidates, target, current + candidates[i], i, list, res);
            list.remove(list.size()-1);
        }
    }
}

// Solution 1: Add or not add
// Time: O(2 ^ n)
// Space: O(n)
// 08/30/2018
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, res, 0, target, new ArrayList<>());
        return res;
    }
    
    private void backtrack(int[] candidates, List<List<Integer>> res, int index, int target, List<Integer> current) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        } else if (index >= candidates.length || target < 0) {
            return;
        } else {
            backtrack(candidates, res, index + 1, target, current);
            current.add(candidates[index]);
            backtrack(candidates, res, index, target - candidates[index], current);
            current.remove(current.size() - 1);
        }
    }
}
