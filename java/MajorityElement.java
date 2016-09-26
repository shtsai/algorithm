/*
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class Solution {
    public int majorityElement(int[] nums) {

        // Boyer–Moore majority vote algorithm
        int count=0, majority = 0;
        for (int num: nums) {
            if (count==0) {
                majority = num;
                count++;
            } else if (num!=majority) {
                count--;
            } else {
                count++;
            }
        }
        
        // validation
        count = 0;
        for (int i : nums) {
            if (i == majority) {
                count++;
            }
        }
        
        if (count >= nums.length / 2) {
            return majority;
        } else {
            return -1;
        }

        /*
        // brute force, use a HashMap to count occurance
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        
        int maxValue = nums[0];
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxValue = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return maxValue;
        */
    }
}