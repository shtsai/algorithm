/*
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 */


public class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int first = 1;      // a flag variable to determine first k and second k
        
        for (int i = 0; i < chars.length; i += k) {
            if (first > 0) {
                if (i + k <= chars.length) {     // more than k chars left
                    reverse(chars, i, i + k);
                } else {                        // less than k chars left, reverse the remaining chars
                    reverse(chars, i, chars.length);
                }
                first = 0;
            } else {
                first = 1;
            }
        }
        
        return new String(chars);
        
    }
    
    // helper function to reverse chars from start index to end index
    public void reverse(char[] chars, int start, int end) {
        end--;
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}