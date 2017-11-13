/*
 * Given a string, find the first non-repeating character in it and return it's index.
 * If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */

// Solution 3: slow and fast pointers
// Use a slow point to point at the a candidate.
// Use a fast point to scan through the string and update frequency.
// When candidate becomes invalid, we move slow pointer to find next candidate.
// Note that slow pointer might go beyond fast pointer, if that is the case,
// we will need to update frequency along the way, and reset fast
// to slow+1 at the end.
//
// Time: O(n) - worst case two passes
// Space: O(1) - frequency map, constant space
// 11/13/2017

class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] freq = new int[256];
        int slow = 0, fast = 0;
        char[] chars = s.toCharArray();
        while (fast < chars.length) {
            freq[chars[fast]]++;
            while (slow < chars.length && freq[chars[slow]] > 1) {
                slow++;
                if (slow >= chars.length) return -1;
                if (slow > fast) {
                    freq[chars[slow]]++;
                }
            }
            if (slow > fast) {
                fast = slow + 1;
            } else {
                fast++;
            }
        }
        return slow;
    }
}

// Solution 2:
// count the frequency of each char
// return the first char with frequency of 1
// time: O(n), space O(1)-constant space
public class Solution {
    public int firstUniqChar(String s) {
        int[] frequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (frequency[s.charAt(i) - 'a'] == 1) {
               return i;
            }
        }
        return -1;
    }
}

// Solution 1:
// use two sets
// one for duplicate char, the other for good char
// time: O(n), space: O(n)
public class Solution {
    public int firstUniqChar(String s) {
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> OKset = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!OKset.contains(c) && !set.contains(c)) {
                OKset.add(c);
            } else {
                OKset.remove(c);
                set.add(c);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (OKset.contains(c)) {
                return i;
            }
        }
        return -1;
    }
}
