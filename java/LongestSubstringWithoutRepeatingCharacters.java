/*
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0, len = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        
        while (end < s.length()) {
            char c = s.charAt(end);
            if (!map.containsKey(c)) {  // find a non-repeating character
                map.put(c, end);        // record the last appeared index
                len++;
                max = Math.max(max, len);       // update max
            } else {    // find a repeating character
                for (int i = start; i < map.get(c); i++) {
                    map.remove(s.charAt(i));    // remove chars that are no longer part of the substring
                }
                start = map.get(c) + 1;     // move the start pointer to new start point
                len = end - start + 1;
                map.put(c, end);
            }
            end++;
        }
        
        return max;
    }
}