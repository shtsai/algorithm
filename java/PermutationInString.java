/*
    Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
    Example 1:
    Input:s1 = "ab" s2 = "eidbaooo"
    Output:True
    Explanation: s2 contains one permutation of s1 ("ba").
    Example 2:
    Input:s1= "ab" s2 = "eidboaoo"
    Output: False
    Note:
    The input strings only contain lower case letters.
    The length of both given strings is in range [1, 10,000].
 */

// Solution 2: Frequency array without creating new array everytime
// Maintain a left and right pointers for the window
// 
// Time: O(mn)
// Space: O(1)
// 06/11/2018

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for (char c : s1.toCharArray()) {
            freq1[c - 'a']++;
        }
    
        int left = 0, right = 0; 
        for (; right < s1.length() - 1; right++) {
            freq2[s2.charAt(right) - 'a']++;
        }
        
        while (right < s2.length()) {
            freq2[s2.charAt(right) - 'a']++;
            right++;
            if (match(freq1, freq2)) {
                return true;
            } else {
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }
        }
        
        return false;
    }
    
    private boolean match(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}

// Solution 1: Frequency array
// Store the frequency array of target string s1.
// Then compare frequency array of windows of s2.
// 
// Time: O(mn) - m:len(s1), n:len(s2)
// Space: O(n)
// 06/11/2018

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] freq1 = new int[26];
        for (char c : s1.toCharArray()) {
            freq1[c - 'a'] += 1;
        }
    
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] freq2 = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                freq2[s2.charAt(i + j) - 'a'] += 1;
            }
            if (match(freq1, freq2)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean match(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
