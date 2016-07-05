/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 *		pattern = "abba", str = "dog cat cat dog" should return true.
 * 		pattern = "abba", str = "dog cat cat fish" should return false.
 */


public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        
        HashMap map = new HashMap();
        for (Integer i = 0; i < words.length; i++) {
            // put returns the previous value with the key
            // this test checks if the char and the word have the same previous index
            if (map.put(pattern.charAt(i),i) != map.put(words[i],i))
            {
                return false;
            }
        }
        
        return true;
    }
}