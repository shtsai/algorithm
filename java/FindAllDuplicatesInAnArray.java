/*
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements that appear twice in this array.
 */

// Solution 2:
// O(n) time, O(1) space
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // since all integer are 1 <= a[i] <= n, so we can use them as indices
            // if the number has appeared, we changed the number on its index to negative
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] <= 0) {
                result.add(index + 1);
            }
            nums[index] *= -1;
        }
        
        return result;
    }
}

// Solution 1: HashMap
// O(n) time, O(n) space
public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        HashMap<Integer, Integer> f = new HashMap<>();
        for (int num : nums) {
            if (!f.containsKey(num)) {
                f.put(num, 1);
            } else {
                f.put(num, f.get(num) + 1);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (Integer num: f.keySet()) {
            if (f.get(num) == 2) {
                result.add(num);
            }
        }
        
        return result;
    }
}