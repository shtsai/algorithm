/*
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 */

// Solution 1: backtracking
//
// Time: O(2^n)
// Space: O(n)
// 08/30/2018

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, n, k, 1, new ArrayList<>());
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, int n, int k, int index, List<Integer> current) {
        if (k == 0) {
            res.add(new ArrayList<>(current));
            return;
        } else {
            for (int i = index; i <= n; i++) {
                current.add(i);
                backtrack(res, n, k - 1, i + 1, current);
                current.remove(current.size() - 1);
            }
        }
    }
}