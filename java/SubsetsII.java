/*
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 *
 * Note: The solution set must not contain duplicate subsets.
 */

// Solution 1: 
// Similar to subsets.
// Sort the array, and skip duplicate numbers
// 08/20/2018
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, res, 0, new ArrayList<>());
        return res;
    }
    
    private void backtrack(int[] nums, List<List<Integer>> res, int index, List<Integer> current) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            current.add(nums[i]);
            backtrack(nums, res, i + 1, current);
            current.remove(current.size() - 1);
        }
    }
}
