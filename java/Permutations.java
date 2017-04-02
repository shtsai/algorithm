/*
 * Given a collection of distinct numbers, return all possible permutations.
 */

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), nums);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));   // need this
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {   // skip numbers that we already used
                continue;
            }
            list.add(nums[i]);
            helper(result, list, nums);
            list.remove(list.size()-1);
        }
    }   
}