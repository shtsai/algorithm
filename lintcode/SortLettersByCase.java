/*
    Given a string which contains only letters. Sort it by lower case first and upper case second.

     Notice:
        It's NOT necessary to keep the original order of lower-case letters and upper case letters.


    Example:
        For "abAcD", a reasonable answer is "acbAD"
 */

// Solution 1:
// Use two pointers two find misplaced uppercase and lowercase characters,
// and swap them to their correct position.
// Time: O(n)
// Space: O(1)
// 10/05/2017

public class Solution {
    /*
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) return;
        int left = 0, right = chars.length-1;
        while (true) {
            while (left < chars.length && !isUpper(chars[left])) left++;
            while (right >= 0 && isUpper(chars[right])) right--;
            if (left < right) {
                swap(chars, left, right);
            } else {
                break;
            }
        }
    }
    
    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    private void swap(char[] C, int a, int b) {
        char temp = C[a];
        C[a] = C[b];
        C[b] = temp;
    }
}