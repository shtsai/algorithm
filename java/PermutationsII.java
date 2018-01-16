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

// Solution 2:
// Use a boolean array to keep track of whether we used the number before.
// To avoid duplicate, for the same number, we only allow them to go
// from smaller index to larger index.
// Time: O(n! * n) 
// Space: O(n) - boolean array
// 01/15/2018

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(res, nums, used, new ArrayList<>());
        return res;
    }
    
    public void helper(List<List<Integer>> res, int[] nums, boolean[] used, List<Integer> current) {
        if (current.size() == nums.length) {
            res.add(new ArrayList<>(current));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                    continue;
                }
                current.add(nums[i]);
                used[i] = true;
                helper(res, nums, used, current);
                used[i] = false;
                current.remove(current.size()-1);
            }
        }
    }
}

// Solution 1:
// first sort the array to avoid duplicates. 
// Convert the array to an arraylist so that insertion and deletion is easier.
// Then use helper function to recursively build the Solution
// Time: O(n!)
// Space: O(n) - call stack and number array

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
