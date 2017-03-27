/*
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
 * Find the two elements that appear only once.
 *
 * For example:
 *
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

// XOR, bit manipulation
// reference:
// https://discuss.leetcode.com/topic/21605/accepted-c-java-o-n-time-o-1-space-easy-solution-with-detail-explanations/2

public class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        
        xor &= -xor;   // equivalent to xor = xor & (~(xor-1))  // find the set bit, the bit that separates two numbers
        
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if ((xor & nums[i]) != 0) { // set bit is set
                result[0] ^= nums[i];
            } else {                    // set bit is not set
                result[1] ^= nums[i];
            }
        }
        
        return result;
    }
}

/*
// use hashset, extra space
public class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                set.remove(nums[i]);
            }
        }
        
        Integer[] result = set.toArray(new Integer[2]);
        int[] result1 = new int[2];
        for (int i = 0; i < 2; i++) {
            result1[i] = result[i];
        }
        return result1;
    }
}
*/