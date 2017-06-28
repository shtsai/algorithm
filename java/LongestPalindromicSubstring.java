/*
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * 
 * Input: "babad"
 * 
 * Output: "bab"
 * 
 * Note: "aba" is also a valid answer.
 * Example:
 * 
 * Input: "cbbd"
 * 
 * Output: "bb"
 */

// expand from the center, search for palindrome starting from every index
// O(n2) time
public class Solution {
    public String longestPalindrome(String s) {
        int max = 0, start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int[] param = new int[3];   // param[0]=len, param[1]=start, param[2]=end;
            
            // palindrome centered at i
            if ((i - 1 >= 0) && (i + 1 < s.length()) && s.charAt(i-1) == s.charAt(i+1)) {
                param[0] = 1;
                param[1] = i;
                param[2] = i;
                if (findPalindrome(param, s) > max) {
                    max = param[0];
                    start = param[1];
                    end = param[2];
                }
            } 
            
            // palindrome centered at i and i+1
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i+1)) {
                param[0] = 2;
                param[1] = i;
                param[2] = i+1;
                if (findPalindrome(param, s) > max) {
                    max = param[0];
                    start = param[1];
                    end = param[2];
                }
            }
        }
        
        return s.substring(start, end+1);
    }
    
    private int findPalindrome(int[] param, String s) {
        int len = param[0], p = param[1], q = param[2];
        while ((p - 1 >= 0) && (q + 1 < s.length()) && s.charAt(p-1) == s.charAt(q+1)) {
                len += 2;
                p--;
                q++;
        }
        param[0] = len;
        param[1] = p;
        param[2] = q;
        return len;
    }
}