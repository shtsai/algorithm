/*
 * Given an integer array of size n, find all elements that appear more than n/3 times. 
 * The algorithm should run in linear time and in O(1) space.
 */

// use the same approach as MajorityElement.java
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        
        // there could only be at most two elements appear more than n/3 times
        int first = nums[0];
        int firstCount = 0;
        int second = nums[0];
        int secondCount = 0;
        
        for (int num : nums) {
            if (num == first) {   // find a match for the first element
                firstCount++;
            } else if (num == second) {  // find a match for the second element
                secondCount++;
            } else if (firstCount == 0) { // find a new element to replace first
                first = num;
                firstCount++;
            } else if (secondCount == 0) { // find a new element tp replace second
                second = num;
                secondCount++;
            }  else {             // find an element that is neither first nor second
                firstCount--;     // second majority elements appear more than n/3 times
                secondCount--;    // we can "cancel out" these three elements at the same time
            }                     // while the majority elements remain unchanged
        }
       
        // need validation
        firstCount = 0;
        secondCount = 0;
        for (int num: nums) {
            if (num == first) firstCount++;
            else if (num == second) secondCount++;
        }
        if (firstCount > nums.length/3) result.add(first);
        if (secondCount > nums.length/3) result.add(second);
        
        return result;
    }
}