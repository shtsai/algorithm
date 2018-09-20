/*
	Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

	Example 1:
	Input: "aacecaaa"
	Output: "aaacecaaa"
	
	Example 2:
	Input: "abcd"
	Output: "dcbabcd"
 */

// Solution 3: KMP
// https://leetcode.com/problems/shortest-palindrome/solution/


// Solution 2: Recursion
// https://leetcode.com/problems/shortest-palindrome/solution/
// Time: O(n ^ 2)
// Space: O(1)
// 09/20/2018
class Solution {
    public String shortestPalindrome(String s) {
        int i = 0;
        for (int j = s.length() - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }
        if (i == s.length()) {
            return s;
        }        
        String rev = (new StringBuilder(s.substring(i))).reverse().toString();
        return rev + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }
}

// Solution 1: Brute force
// Time: O(n ^ 2)
// Space: O(1)
// 09/20/2018
class Solution {
    public String shortestPalindrome(String s) {
        String rev = (new StringBuilder(s)).reverse().toString();
        for (int i = 0; i < rev.length(); i++) {
            if (isPalindrome(rev.substring(0, i) + s)) {
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }
    
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
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