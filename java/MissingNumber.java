/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example, Given nums = [0, 1, 3] return 2.
 */

// XOR approach
// for any number x, x ^ x = 0 and x ^ 0 = x
// therefore, if we XOR all the indice and number together,
// all the numbers will be cancelled out except the missing number

public class Solution{
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int xor = len;
        for (int i = 0; i<len; i++) {
            xor = xor ^ i ^ nums[i];
        }
        
        return xor;
    }
}


/*
// Sum approach
// take the difference between a full array and a missing array
// the result is the missing number
// much faster than set approach

public class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = len;
        for (int i = 0; i < len; i++) {
            sum = sum + i - nums[i];
        }
        return sum;
    }
}
*/


/*
// Set approach slow because of two for loops
public class Solution {
    public int missingNumber(int[] nums) {

        int len = nums.length;

        Set<Integer> set = new HashSet<Integer>();

        // first add all numbers to the set        
        for (int i = 0; i<len; i++) {
            set.add(nums[i]);
        }

        // then check if the set contains all numbers
        // if not, return that number
        for (int i = 0; i < len+1; i++) {
            if (!set.contains(i)){
                return i;
            }
        }
        
        return len+1;
    }
}
*/