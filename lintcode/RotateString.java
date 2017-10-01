/*
    Given a string and an offset, rotate string by offset. (rotate from left to right)

    Have you met this question in a real interview? Yes
    Example
    Given "abcdefg".

    offset=0 => "abcdefg"
    offset=1 => "gabcdef"
    offset=2 => "fgabcde"
    offset=3 => "efgabcd"
 */

// Solution 1: reverse
// First reverse the whole string,
// Then reverse [0:offset), and finally reverse [offset: end]
// Note that need to take mod on offset b/c it could be greater than the length
// Time: O(n)
// Space: O(1)
// 10/01/2017

public class Solution {
    /*
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0) return;
        offset = offset%str.length;
        reverse(str, 0, str.length-1);
        reverse(str, 0, offset-1);
        reverse(str, offset, str.length-1);
    }
    
    private void reverse(char[] c, int l, int r) {
        while (l < r) {
            char temp = c[l];
            c[l] = c[r];
            c[r] = temp;
            l++;
            r--;
        }
    }
}