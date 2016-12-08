/*
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, 
 * and it should return false if every element is distinct.
 */

public class Solution {
    // Hashset, O(n) time, O(n) space
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    // There are also other solutions
    // 1. brute force, compare all pairs, O(n^2) (Time limit exceeded), no additional memory
    // 2. sort, and compare all adjacent nums, O(nlogn)
}