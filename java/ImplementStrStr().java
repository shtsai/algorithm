/*
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

// Solution 3:
// elegent solution from: https://discuss.leetcode.com/topic/18839/elegant-java-solution
public int strStr(String haystack, String needle) {
    for (int i = 0; ; i++) {
        for (int j = 0; ; j++) {
        if (j == needle.length()) return i;
        if (i + j == haystack.length()) return -1;
        if (needle.charAt(j) != haystack.charAt(i + j)) break;
        }
    }
}

// Solution 2:
// use substring approach, much faster
class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen == 0) return 0;
        if (hLen < nLen) return -1;
        
        for (int i = 0; i < hLen-nLen+1; i++) {        // pointer on haystack
            if (haystack.substring(i, i+nLen).equals(needle)) {       // find first char
                return i;
            }
        }
        return -1;
    }
}

// Solution 1:
// brute force solution
public class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) return -1;     // needle must be longer than haystack
        if (needle.length() == 0 || haystack.equals(needle)) return 0;  // special cases
        for (int i = 0; i < haystack.length()-needle.length()+1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {  // look closer when matches first char
                int result = i;
                for (int j = 1; j < needle.length(); j++) {
                    if (haystack.charAt(i+j) != needle.charAt(j)) {  // break if no match
                        result = -1;
                        break;
                    }
                    if (j == needle.length()-1) { // matches all char from 0 to j
                        result = i;
                    }
                }
                if (result != -1) return result;
            }
        }        
        return -1;
    }
}

// Solution 1 version 2:
// brute force solution
class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length(), nLen = needle.length();
        if (nLen == 0) return 0;
        if (hLen < nLen) return -1;
        
        for (int i = 0; i < hLen-nLen+1; i++) {        // pointer on haystack (use hLen-nLen+1 to save some cycles)
            if (haystack.charAt(i) == needle.charAt(0)) {       // find first char
                int j = 1;   // <-- pointer on needle, j can start from 1 
                while (j < nLen && (i+j < hLen) && haystack.charAt(i+j) == needle.charAt(j)) {  // continue check for remaining chars
                    j++;
                }
                if (j == nLen) return i;        // find all chars in needle, return the start of the needle
            }
        }
        return -1;
    }
}