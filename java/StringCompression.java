/*
    Given an array of characters, compress it in-place.

    The length after compression must always be smaller than or equal to the original array.

    Every element of the array should be a character (not int) of length 1.

    After you are done modifying the input array in-place, return the new length of the array.


    Follow up:
    Could you solve it using only O(1) extra space?


    Example 1:
    Input:
    ["a","a","b","b","c","c","c"]

    Output:
    Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

    Explanation:
    "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
    Example 2:
    Input:
    ["a"]

    Output:
    Return 1, and the first 1 characters of the input array should be: ["a"]

    Explanation:
    Nothing is replaced.
    Example 3:
    Input:
    ["a","b","b","b","b","b","b","b","b","b","b","b","b"]

    Output:
    Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

    Explanation:
    Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
    Notice each digit has it's own entry in the array.
    Note:
    All characters have an ASCII value in [35, 126].
    1 <= len(chars) <= 1000.
 */

// Solution 1: two pointers
// One pointer scans through the array,
// the other pointer points at the write index.
// When we encounter the same number as the previous one,
// we increment counter by 1.
// When we encounter a different number, we check if 
// count is greater than 1.
// If so, we write the counter to write index, and increment
// write pointer accordingly.
// Note that count could be multiple digits.
// After a write, we update current char and reset count to one.
// And we write current char to write index, because we always
// record the first occurence.
//
// Time: O(n) - one pass
// Space: O(1)
// 11/08/2017

// Version 2:   shorter and cleaner
// reference: https://leetcode.com/articles/string-compression/
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) return 0;
        int write = 0, current = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read] != chars[read+1]) {  // end of a block
                chars[write++] = chars[current];    // write starting char
                if (read > current) {       // count is greater than 1
                    // sneaky approach to convert int to chars  
                    for (char c : ("" + (read - current + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                current = read+1;
            }
        }
        return write;
    }
}

// Version 1:
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) return 0;
        char count = 1, windex = 1;
        char c = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (c == chars[i]) {
                count++;
            } else {
                if (count > 1) {
                    String scount = Integer.toString(count);
                    for (int j = 0; j < scount.length(); j++) {
                        chars[windex++] = scount.charAt(j);
                    }
                    
                }
                count = 1;
                c = chars[i];
                chars[windex++] = c;
            }
        }
        if (count > 1) {
            String scount = Integer.toString(count);
            for (int i = 0; i < scount.length(); i++) {
                chars[windex++] = scount.charAt(i);
            }
        }
        return windex;
    }
}