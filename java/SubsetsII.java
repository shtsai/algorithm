/*
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 *
 * Note: The solution set must not contain duplicate subsets.
 */

// very similiar to subsets, need to take care of duplicates
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        helper(result, new ArrayList<>(), 0, nums);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> list, int start, int[] nums) {
        // base case
        if (start > nums.length) return;
        
        // add the deep copy of list to the result
        result.add(new ArrayList<Integer>(list));
        
        int i = start;
        while (i < nums.length) {
            list.add(nums[i]);
            helper(result, list, i+1, nums);
            list.remove(list.size()-1);
            i++;
            // skip duplicate elements
            while (i < nums.length && nums[i] == nums[i-1]) i++;    
        }
        return;
    }
}