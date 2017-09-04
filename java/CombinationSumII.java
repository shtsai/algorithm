/*
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 * Note:
 *
 *    All numbers (including target) will be positive integers.
 *    The solution set must not contain duplicate combinations.
 *
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * 
 * [
 *  [1, 7],
 *  [1, 2, 5],
 *  [2, 6],
 *  [1, 1, 6] Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:

[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

 * ]
 */

// Solution 1:
// backtracking
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        Arrays.sort(candidates);    // sort in ascending oreder
        helper(res, candidates, target, 0, new ArrayList<Integer>());
        return res;
    }

    private void helper(List<List<Integer>> res, int[] candidates, int target, int start, ArrayList<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0) return;   // target must be > 0
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]) {    // find duplicates
                continue;       // skip duplicate element
            }
            list.add(candidates[i]);
            helper(res, candidates, target-candidates[i], i+1, list);   // i+1 here because we cannot reuse same element
            list.remove(list.size()-1);
        }
    }
}
