/*
	Given a non-empty array of numbers, a_0, a_1, a_2, … , a_{n-1}, where 0 ≤ a_i < 2^31.

	Find the maximum result of a_i XOR a_j, where 0 ≤ i, j < n.

	Could you do this in O(n) runtime?

	Example:

	Input: [3, 10, 5, 25, 2, 8]

	Output: 28

	Explanation: The maximum result is 5 ^ 25 = 28.
 */

// Solution 2: Trie
// Build a Trie for all digits.
// Then for each number, try find the other number that is as different as possible
// 
// Time: O(n * 32) = O(n)
// Space: O(n)
// 07/31/2018
	
class Solution {
    private class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        Trie root = new Trie();
        // build trie
        for (int num : nums) {
            Trie current = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                if (current.children[bit] == null) {
                    current.children[bit] = new Trie();
                }
                current = current.children[bit];
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie current = root;
            int sum = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                if (current.children[bit ^ 1] != null) {
                    sum += (1 << i);
                    current = current.children[bit ^ 1];
                } else {
                    current = current.children[bit];
                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}

// Solution 1: Compare every pair
// Time: O(n^2)
// Space: O(1)
// 07/31/2018

class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}