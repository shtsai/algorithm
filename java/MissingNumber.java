/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example, Given nums = [0, 1, 3] return 2.
 */

// Solution 3: XOR approach
// for any number x, x ^ x = 0 and x ^ 0 = x
// therefore, if we XOR all the indice and number together,
// all the numbers will be cancelled out except the missing number
// Time: O(n) - one pass
// Space: O(1)
// 01/12/2018
class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}


// Solution 2: Sum approach
// take the difference between a full array and a missing array
// the result is the missing number
// much faster than set approach
// Time: O(n) - one pass
// Space: O(1)
// 01/12/2018
class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res += i - nums[i];
        }
        return res;
    }
}


// Solution 1: Set
// Store all numbers into a HashSet.
// Then scan from 0 - n to find the missing one.
// Time: O(n) - two-pass
// Space: O(n)
// 01/12/2018
class Solution {
    public int missingNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length;
    }
}