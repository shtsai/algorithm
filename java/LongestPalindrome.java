/* 
 * Given a string which consists of lowercase or uppercase letters, 
 * find the length of the longest palindromes that can be built with those letters.
 * 
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 */

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