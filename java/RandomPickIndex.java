/*
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * 
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * 
 * Example:
 * 
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 */

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

// Solution 2:
// Reservoir sampling
// reference: 
// https://en.wikipedia.org/wiki/Reservoir_sampling
// https://discuss.leetcode.com/topic/58301/simple-reservoir-sampling-solution
// Time: O(n)
// Space: O(1) - no extra space
// 09/12/2018
public class Solution {
    int[] nums;
    Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
    
    public int pick(int target) {
        int count = 0;
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;    // skip non-target numbers
            count++;
            if (rand.nextInt(count) == 0) {   // with 1/count probability to swap
                result = i;
            }
        }
        return result;
    }
}

// Solution 1: HashMap
// create a hashmap with <int, indices> map
// use random number generator to pick an index from the indices
// Time: init - O(n)
//       pick - O(1)
// Space: O(n)
// 09/12/2018
class Solution {
    HashMap<Integer, List<Integer>> map;
    Random rand;
    
    public Solution(int[] nums) {
        rand = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> list = map.get(target);
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
