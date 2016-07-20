/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Given s = "hello", return "holle".
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 */


public class Solution {
    public String reverseVowels(String s) {
        int length = s.length();
        int[] index = new int[length];
        char[] vowels = new char[length];
        char[] chars = new char[length];
        int num = 0;
        
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                index[num] = i;
                vowels[num] = c;
                num++;
            };
            chars[i] = c;
        }
        
        for (int i = 0; i < num; i++) {
            chars[index[i]] = vowels[num-1-i];
        }
        
        String result = new String(chars);
        
        return result;
    }
}