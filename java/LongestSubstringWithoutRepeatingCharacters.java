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

// Solution 2: One pass + HashMap
// Store the last occurrence index for the duplicate char
//
// Time: O(n) - one pass
// Space: O(n) - HashMap
// 09/11/2018
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)));
            }
            max = Math.max(max, right - left + 1);
            map.put(s.charAt(right), right + 1);
        }
        return max;
    }
}

// Solution 1: Sliding window + HashSet
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
