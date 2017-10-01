/*
    Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

    The input string does not contain leading or trailing spaces and the words are always separated by a single space.

    For example,
    Given s = "the sky is blue",
    return "blue is sky the".

    Could you do it in-place without allocating extra space?
 */

// Solution 1:
// Reverse the whole array, then reverse each word
// Time: O(n) - two-pass
// Space: O(1)
// 09/30/2017

public class Solution {
    public void reverseWords(char[] s) {
        int len = s.length;
        reverse(s, 0, len-1);
        int start = 0;
        for (int end = 1; end < len; end++) {
            if (s[end] == ' ') {
                reverse(s, start, end-1);
                start = end+1;
            }
        }
        reverse(s, start, len-1);   // reverse last word
    }
    
    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}