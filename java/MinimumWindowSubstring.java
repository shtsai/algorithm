/*
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * 
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

// Solution 1: version 2
// HashMap version
// Time: O(n) - n = s.length()
// Space: O(n)
// 09/27/2017
class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count = t.length();
        for (int i = 0; i < t.length(); i++) {  // store char frequency of t
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
            } else {
                map.put(c, -1);
            }
        }
        
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE, minLeft = 0, minRight = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                if (map.get(c) < 0) {
                    count--;
                }
                map.put(c, map.get(c)+1);
                
            }
            right++;
            while (left < right && count == 0) {   // find all chars
                if ((right - left) < min) { // update min
                    min = right - left;
                    minLeft = left;
                    minRight = right;
                }
                c = s.charAt(left);
                if (map.containsKey(c)) {
                    if (map.get(c) == 0) {  // find a new valid start
                        count++;
                    }
                    map.put(c, map.get(c)-1);
                }
                left++;
            }
        }
        
        return s.substring(minLeft, minRight);
    }
}

// Solution 1: Map and two pointers
// reference: https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems/12

public class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int count = t.length();
        for (int i = 0; i < count; i++) {      // add chars in t into map
            map[t.charAt(i)]++;
        }
        
        int min = Integer.MAX_VALUE, start = 0, end = 0, head = 0;
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0) {     // find a char in t
                count--;
            }
            map[s.charAt(end)]--;
            end++;
            
            while (count == 0) {    // find all chars in t
                if (end - start < min) {    // find a smaller min
                    min = end - start;
                    head = start;
                }
                map[s.charAt(start)]++;     // restore char
                if (map[s.charAt(start)] > 0) {     // invalidate current substring and continue search
                    count++;
                }
                start++;    // find right start position
            }
        }
        
        return min == Integer.MAX_VALUE ? "" : s.substring(head, head+min);
    }
}