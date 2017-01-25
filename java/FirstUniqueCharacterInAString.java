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


// count the frequency of each char
// return the first char with frequency of 1
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

/*
// use two sets
// one for duplicate char, the other for good char
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
*/