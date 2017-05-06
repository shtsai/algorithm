/*
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital if it has more than one letter, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 */

public class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        
        // if first char is uppercase
        if (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
            boolean allUpper = true, allLower = true;
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                    allLower = false;
                }
                if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                    allUpper = false;
                }
            }
            return allLower || allUpper;
        } else {
            // first char is lowercase
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                    return false;
                }
            }
            return true;
        }
    }
}