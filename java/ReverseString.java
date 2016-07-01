/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh".
 */

public class Solution {
    public String reverseString(String s) {
        int length = s.length();
        char[] chars = new char[length];
        
        for (int i = 0; i < length; i++) {
            chars[i] = s.charAt(length-1-i);
        }
        
        return new String(chars);
    }
}