/*
	We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).

	Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.

	Example 1:
	Input: 
	bits = [1, 0, 0]
	Output: True
	Explanation: 
	The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
	Example 2:
	Input: 
	bits = [1, 1, 1, 0]
	Output: False
	Explanation: 
	The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
	Note:

	1 <= len(bits) <= 1000.
	bits[i] is always 0 or 1.
 */

// Solution 3: 
// Observation: 0 can only form 1-bit characters, 1 can only form 2-bit characters.
// Therefore, when we see 0, increment pointer by one.
// When we see 1, increment pointer by two.
// Finally, see if pointer is pointing at the last index.
// Time: O(n)
// Space: O(1)
// 01/12/2018
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 0) {   // 0 can only form 1-bit characters
                i++;
            } else {    // '1'  can only form 2-bit characters
                i += 2;
            }
        }
        return i == bits.length-1;  // check whether the last character is 1-bit
    }
}

// Solution 2: Recursion with memoization
// Time: O(n)
// Space: O(n)
// 01/12/2018
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int[] memo = new int[bits.length];
        return search(bits, 0, 0, memo);
    }
    
    public boolean search(int[] bits, int index, int cur, int[] memo) {
        if (index == bits.length) {
            return cur == 0;    // check if last character is one bit 
        }
        if (memo[index] != 0) return memo[index] == 1 ? true : false;
        
        if (bits[index] == 0 && search(bits, index+1, 0, memo)) {
            memo[index] = 1;
            return true;
        } 
        if (bits[index] != 0 && index + 1 < bits.length && search(bits, index+2, bits[index]+bits[index+1], memo)) {
            memo[index] = 1;
            return true;
        } 
        
        memo[index] = -1;
        return false;
        
    }
}

// Solution 1: Recursion
// Try all ways of decoding, and check the last character in every encoding.
// Time: O(2^n)
// Space: O(1)
// 01/12/2018
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        return search(bits, 0, 0);
    }
    
    public boolean search(int[] bits, int index, int cur) {
        if (index == bits.length) {
            return cur == 0;    // check if last character is one bit 
        }
        if (bits[index] == 0 && search(bits, index+1, 0)) {
            return true;
        } 
        if (bits[index] != 0 && index + 1 < bits.length && search(bits, index+2, bits[index]+bits[index+1])) {
            return true;
        } 
        return false;       
    }
}