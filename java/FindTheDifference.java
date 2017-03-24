/*
 * Given two strings s and t which consist of only lowercase letters.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Find the letter that was added in t.
 */

// use XOR
public class Solution {
    public char findTheDifference(String s, String t) {
        char result = t.charAt(t.length()-1);
        
        // the characters that appear twice will be cancelled out
        for (int i = 0; i < s.length(); i++) {
            result ^= t.charAt(i);
            result ^= s.charAt(i);
        }

        return result;
    }
}

/*
// use character addition and subtraction
public class Solution {
    public char findTheDifference(String s, String t) {
        char result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            result += t.charAt(i); // add all characters in t
            result -= s.charAt(i); // and subtract all characters in s
        }
        result += t.charAt(t.length()-1); // since t is longer than s by 1, need to add last character of t

        return result;
    }
}
*/