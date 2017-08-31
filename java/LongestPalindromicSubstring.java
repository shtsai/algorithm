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

// Solution 2, version 2:
// use helper function to find Palindrome, cleaner and faster
class Solution {
    private int max, maxLeft;
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        max = 0;
        maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(s, i, i);        // one char as center
            findPalindrome(s, i, i+1);      // two chars as center
        }
        return s.substring(maxLeft, maxLeft+max);
    }
    
    private void findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        if (max < right-left-1) {
            max = right-left-1;
            maxLeft = left+1;
        }
    }
}

// Solution 2:
// try centering at every position, use one char or two chars as center
// time: O(n2)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int max = 0, maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            int oneLeft = i, oneRight = i;
            int twoLeft = i, twoRight = i+1;
            while (oneLeft >= 0 && oneRight < s.length() && s.charAt(oneLeft)==s.charAt(oneRight)) {
                oneLeft--;
                oneRight++;
            }
            if (max < oneRight-oneLeft-1) {
                max = oneRight-oneLeft-1;
                maxLeft = oneLeft+1;
                
            }
            while (twoLeft >= 0 && twoRight < s.length() && s.charAt(twoLeft)==s.charAt(twoRight)) {
                twoLeft--;
                twoRight++;
            }
            if (max < twoRight-twoLeft-1) {
                max = twoRight-twoLeft-1;
                maxLeft = twoLeft+1;
            }
        }
        return s.substring(maxLeft, maxLeft+max);
    }
}

// Solution 1:
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