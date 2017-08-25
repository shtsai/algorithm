/*
 * Given an input string, reverse the string word by word.
 * 
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * 
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 */

// Solution 2:
// reverse the whole string, then reverse each word
// finally remove whitespaces
// does not use built-in functions
public class Solution {
    public String reverseWords(String s) {
        if (s == null) return null;
        char[] words = s.toCharArray();
        reverse(words, 0, words.length-1);   // reverse whole string
        reverseWords(words);
        return reduceWhiteSpace(words);
    }
    
    private String reduceWhiteSpace(char[] words) {
        int i = 0, j = 0, len = words.length;   // i points to new string, j points to old string
        while (j < len) {
            while (j < len && words[j] == ' ') j++; // skip leading whitespace
            while (j < len && words[j] != ' ') words[i++] = words[j++]; // move words
            while (j < len && words[j] == ' ') j++;
            if (j < len) words[i++] = ' ';
        }
        return new String(words).substring(0, i);
    }
    
    private void reverseWords(char[] words) {
        // i points to the end of a word, j points to the start of a word
        int i = 0, j = 0, len = words.length;
        while (i < len) {
            while (j < i || (j < len && words[j] == ' ')) j++;  // skip whitespace
            while (i < j || (i < len && words[i] != ' ')) i++;  // skip word
            reverse(words, j, i-1);
        }
    }
    
    private void reverse(char[] words, int start, int end) {
        while (start < end) {
            char temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
    }
}

// Solution 1:
// use built-in functions (trim(), split()) and StringBuilder
public class Solution {
    public String reverseWords(String s) {
    	// trim() removes leading and trailing spaces
    	// " +" represents any number of whitespaces
        String[] words = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length-1; i > 0; i--) {	// append words in reverse order
            sb.append(words[i] + " ");
        }
        sb.append(words[0]);
        return sb.toString();

    }
}