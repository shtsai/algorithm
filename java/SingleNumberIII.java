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

// Solution 2:
// XOR, bit manipulation
// Since there are two single numbers, there must be a bit where two numbers are different.
// eg. [1, 1, 7, 3, 2, 2]

// 1:   0001
// 1:   0001
// 7:   0111
// 3:   0011
// 2:   0010
// 2:   0010
// -----------
// XOR->0100     This means that two numbers are different in this bit
//
// We can then separate the numbers by this bit and XOR each group
// 7:   0111      =>  0111 (7)
// -----------------
// 1:   0001
// 1:   0001
// 3:   0011      =>  0011 (3)
// 2:   0010
// 2:   0010
//
// Time: O(n)
// Space: O(1)
// 08/31/2018
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        int i = 0;
        while (i < 32 && ((xor >> i) & 1) == 0) {     // find a set bit in XOR
            i++;
        }
        
        int[] res = new int[2];
        for (int n : nums) {
            if (((n >> i) & 1) == 0) {
                res[0] ^= n;
            } else {
                res[1] ^= n;
            }
        }
        return res;
    }
}

// Solution 1: HashSet
// Time: O(n)
// Space: O(n)
// 08/31/2018
class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int n : nums) {
            if (!set.contains(n)) {
                set.add(n);
            } else {
                set.remove(n);
            }
        }
        
        Iterator it = set.iterator();
        int[] result = new int[2];
        for (int i = 0; i < 2; i++) {
            result[i] = (int) it.next();
        }
        return result;
    }
}