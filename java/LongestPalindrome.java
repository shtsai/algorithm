/* 
 * Given a string which consists of lowercase or uppercase letters, 
 * find the length of the longest palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 * 
 * Input:
 * "abccccdd"
 * 
 * Output:
 * 7
 * 
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7. 
 */

// Solution 2:
// Use an array to record the frequency of each characters
// If a character appears two times, it can be used to form a palindromes, thus we add 2 to the result
// In addition, if a number appears once, it can be placed at the middle of the palindrome,
// but only one such character is allowed
public class Solution {
    public int longestPalindrome(String s) {
        int[] freq = new int[256];
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c]++;
            if (freq[c] == 2) {
                result += 2;
                freq[c] = 0;
            }
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)] == 1) {
                return result + 1;
            }
        }
        
        return result;
    }
}

// Solution 1:
class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] lowercase = new int[26];
        int[] uppercase = new int[26];
        for (char c : s.toCharArray()) {    // get frequency of characters
            if (c >= 'a' && c <= 'z') lowercase[c-'a']++;
            if (c >= 'A' && c <= 'Z') uppercase[c-'A']++;
        }
        boolean singleChar = false;     // a flag indicating that a single character exists (can be put in the middle)
        int count = 0;
        for (int freq : lowercase) {
            if (freq >= 2) {    // has more than two, can be add to palindrome
                int remainder = freq % 2;
                count += freq - remainder;
                if (remainder == 1) singleChar = true;
            } else if (freq == 1) singleChar = true;
        }
        for (int freq : uppercase) {
            if (freq >= 2) {    // has more than two, can be add to palindrome
                int remainder = freq % 2;
                count += freq - remainder;
                if (remainder == 1) singleChar = true;
            } else if (freq == 1) singleChar = true;
        }
        if (singleChar) count++;
        return count;
    }
}
