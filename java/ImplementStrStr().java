/*
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

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