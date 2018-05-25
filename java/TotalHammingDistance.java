/*
    The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

    Now your job is to find the total Hamming distance between all pairs of the given numbers.

    Example:
    Input: 4, 14, 2

    Output: 6

    Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
    showing the four bits relevant in this case). So the answer will be:
    HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
    Note:
    Elements of the given array are in the range of 0 to 10^9
    Length of the array will not exceed 10^4.
 */

// Solution 2: Iterate over bits
// Count the total number of 1-bits b in each position
// The total hamming distance for that position is b * (n - b)
// Finally, sum up hamming distance for all positions
//
// Time: O(n * 32) = O(n)
// Space: O(n)
// 05/25/2018

class Solution {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        int[] bits = new int[32];
        for (int num : nums) {  // count # of 1-bit in each position
            int i = 0;
            while (num != 0) {
                bits[i++] += (num & 1);
                num = num >>> 1;
            }
        }
        
        for (int b : bits) {
            sum += b * (nums.length - b);
        }
        return sum;
    }
}

// Solution 1: Brute force + XOR
// Compare every pair of two numbers by performing XOR
// and count the number of 1-bits
// Time: O(n^2 * 32) = O(n^2) - Time limit exceeded
// Space: O(1)
// 05/25/2018
class Solution {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[i] ^ nums[j];
                while (diff != 0) {
                    sum += (diff & 1);
                    diff = diff >>> 1;
                }
            }
        }
        return sum;
    }
}