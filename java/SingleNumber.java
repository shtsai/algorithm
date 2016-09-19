/**
 *  Given an array of integers, every element appears twice except for one. Find that single one.
 */

public class Solution {
    public int singleNumber(int[] nums) {
        
        // use bit manipulation
        // 0 ^ number = number
        // number ^ number = 0
        
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
        
        
        /*
        // use a hash map to keep track of the occurence of each number
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                int count = 0;
                count = map.get(nums[i]);
                map.put(nums[i], count + 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count == 1) {
                return entry.getKey();
            }
        }
        
        throw new IllegalArgumentException("No single element");
        */
    }
}