/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
 * return the length of last word in the string.
 * If the last word does not exist, return 0.
 */

public class Solution {
    public int lengthOfLastWord(String s) {
        
        String[] stringArray = s.split(" ", -1);
        
        int length = stringArray.length;
        int lastIndex = length - 1;
        String lastWord = stringArray[lastIndex];
        
        while (lastWord.length() == 0 && lastIndex > 0) {
            lastIndex--;
            lastWord = stringArray[lastIndex];
        }
        
        return lastWord.length();
    }
}