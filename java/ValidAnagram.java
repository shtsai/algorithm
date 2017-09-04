/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 */

// Solution 2: version 2
// process two strings in one loop, save one iteration
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            // get frequency of chars in s
            freq[s.charAt(i)-'a']++;
            // subtract frequency of chars in t
            freq[t.charAt(i)-'a']--;
        }

        for (int i : freq) {
            if (i != 0) return false;
        }
        return true;
    }
}


// Solution 2:
// first get the frequency of chars in s, then subtract frequency of chars in t
// if t is an anagram of s, the frequency array should be all zero
// time: O(n), space(1)
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];
        // get frequency of chars in s
        for (char c : s.toCharArray()) {
            freq[c-'a']++;
        }
        // subtract frequency of chars in t
        for (char c : t.toCharArray()) {
            freq[c-'a']--;
        }
        for (int i : freq) {
            if (i != 0) return false;
        }
        return true;
    }
}


// Solution 1:
// sort two strings, and compare them character by Character
// time: O(nlogn) - b/c of sorting
// space: O(1)
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // convert two strings to char arrays
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // sort two arrays
        Arrays.sort(sArray);
        Arrays.sort(tArray);

        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] != tArray[i]) {
                return false;
            }
        }

        return true;
    }
}
