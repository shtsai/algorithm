/*
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */

// Solution 1:
// first sort the array, and convert it to an arraylist so that insertion and deletion is easier
// then use helper function to recursively build the Solution
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        ArrayList<Integer> number = new ArrayList<>();
        for (int i : nums) number.add(i);
        helper(number, res, new ArrayList<Integer>(), nums.length);
        return res;
    }
    
    private void helper(ArrayList<Integer> number, List<List<Integer>> res, ArrayList<Integer> list, int len) {
        if (list.size() == len) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < number.size(); i++) {
            int n = number.get(i);
            // Before we add a number to the list, need to check if we have seen the same number before
            if (i > 0 && number.get(i-1) == n) continue;    // duplicate number, skip
            list.add(n);
            number.remove(i);
            helper(number, res, list, len);
            number.add(i, n);
            list.remove(list.size()-1);
        }
    }
}
