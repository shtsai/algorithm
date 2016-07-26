/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 */

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