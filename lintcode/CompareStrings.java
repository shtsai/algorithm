/*
 * Compare two strings A and B, determine whether A contains all of the characters in B.
 * The characters in string A and B are all Upper Case letters.
 */

public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        if (A.length() < B.length()) {
            return false;
        }

        // use an array to keep track of the occurence of the characters
        // we can use 26 because given all characters are upper case letters
        int[] count = new int[26];
        
        // add 1 for each occurence in A
        for (int i = 0; i < A.length(); i++) {
            int ascii = (int) A.charAt(i);
            count[ascii - 65]++;
        }
        
        // subtract 1 for each occurence in B
        // if A contains all characters from B, the count should never fall below 0 
        // if it did, return false
        for (int j = 0; j < B.length(); j++) {
            int ascii = (int) B.charAt(j);
            count[ascii - 65]--;
            if ((count[ascii - 65]) < 0) {
                return false;
            }
        }
        
        return true;
    }
}