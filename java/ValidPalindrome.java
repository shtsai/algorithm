/*
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.
 *
 *For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 */

// Solution 2: 
// Sightly cleaner version
// Uses built-in Character functions isLetterOrDigit() and toLowerCase()
// Instead of converting whole string to lower case, convert only when
// compare characters
//
// Time: O(n)
// Space: O(1)
// 
// -- 09/21/2017

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        int left = 0, right = s.length()-1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) return false;
        }
        return true;
    }
}

// Solution 1: Two pointers
// First convert whole string to lower case.
// Then use two pointers head and tail.
// Skip all the invalid chars, and do the comparison.
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        int start = 0;
        int end = s.length() - 1;
        s = s.toLowerCase();    // convert the whole string into lower case
        while (start < end) {   // use two pointers
            // check if the char is alphanumeric, if not, skip it
            if (!(s.charAt(start) >= 'a' && s.charAt(start) <= 'z') && !(s.charAt(start) >= '0' && s.charAt(start) <= '9')) {
                start++;
                continue;
            } else if (!(s.charAt(end) >= 'a' && s.charAt(end) <= 'z') && !(s.charAt(end) >= '0' && s.charAt(end) <= '9')) {
                end--;
                continue;
            } else if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}