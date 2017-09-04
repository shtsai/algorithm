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
// backtracking
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        helper(res, candidates, target, 0, new ArrayList<Integer>());
        return res;
    }

    private void helper(List<List<Integer>> res, int[] candidates, int target, int index, ArrayList<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0) return;     // all number will be positive
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(res, candidates, target-candidates[i], i, list); // <- the index is i here, because we can reuse the same element
            list.remove(list.size()-1);
        }
    }
}
