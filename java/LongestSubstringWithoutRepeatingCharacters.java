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

// Solution 2: two pointers and HashSet
// Use a HashSet to keep track of the characters that appears
// in current window.
// At the start of each iteration, we check if the new char is
// already in the set. If so, we increment left pointer and remove
// chars from the window until new char is not in the hash set.
// Then we update windows, hashset, and max.
//
// Time: O(n) - two pointers, two pass worst case
// Space: O(n) - HashSet
// 11/15/2017

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            right++;
            max = Math.max(right-left, max);
        }
        return max;
    }
}

// Solution 1: HashMap and last appeared index
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