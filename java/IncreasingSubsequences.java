/*
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 . 
 *
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */

public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        // use set to avoid duplicates
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        helper(list, nums, 0, result);
        return new ArrayList(result);
    }
    
    private void helper (List<Integer> list, int[] nums, int index, Set<List<Integer>> result) {
        if (list.size() >= 2) {
            result.add(new ArrayList<>(list));
        }

        if (index >= nums.length) return;

        for (int i = index; i < nums.length; i++) {
            if (list.size() == 0 || nums[i] >= list.get(list.size()-1)) {
                list.add(nums[i]);
                helper(list, nums, i+1, result);
                list.remove(list.size()-1);
            }
        }
    }
}