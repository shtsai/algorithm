/**
 *  Given an array of integers, every element appears twice except for one. Find that single one.
 */

// Solution 2: Bit manipulation
// use bit manipulation
// 0 ^ number = number
// number ^ number = 0
// Time: O(n)
// Space: O(1)
public class Solution {
    public int singleNumber(int[] nums) {
        int num = 0;
        for (int n : nums) {
            num ^= n;
        }
        return num;
    }
}


// Solution 1: HashMap
// use a hash map to keep track of the occurence of each number
// Time: O(n)
// Space: O(n)
// 08/31/2018
public class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        throw new IllegalArgumentException("No single element");
    }
}