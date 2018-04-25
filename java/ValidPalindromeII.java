/*
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * 
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000. 
 */

// Solution 2: 
// Pass a substring and a flag to the helper function
// Time: O(n)
// Space: O(1)
// 04/25/2018
class Solution {
    public boolean validPalindrome(String s) {
        return helper(s, false);
    }
    
    public boolean helper(String s, Boolean deleted) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (deleted) {
                return false;
            } else {
                return helper(s.substring(left, right), true) || helper(s.substring(left+1, right+1), true);
            }
        }
        return true;
    }
}

// Solution 1:
// Use a helper function isPalindrome() to check if a substring is palindrome.
// Time: O(n)
// Space: O(1)
//
// 09/21/2017

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int left = 0, right = s.length()-1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) { // mismatch
                    //          delete right           ||  delete left
                return (isPalindrome(s, left, right-1) || isPalindrome(s, left+1, right));
            }
            left++;
            right--;
        }
        return true;
    }
    
    /*  This function checks if a substring is palindrome.  */
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}