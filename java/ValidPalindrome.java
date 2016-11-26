/*
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.
 *
 *For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 */

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