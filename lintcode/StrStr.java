/*
 * For a given source string and a target string, you should output the first index(from 0) of target string in source string.
 * If target does not exist in source, just return -1.
 */

class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here

        // n2 implementation
        
        // special cases
        if (target == null || source == null) {
            return -1;
        }
        if (target.length() == 0) {
            return 0;
        }
    
        // a flag variable
        boolean match = false;
       
        for (int i = 0; i < source.length(); i++) {
            for (int j = 0; j < target.length(); j++) {
                int index = i + j;
                if (index >= source.length()) {
                    match = false;
                    break;
                }
                if (source.charAt(index) == target.charAt(j)) {
                    match = true;
                } else {
                    match = false;
                }
                
                // if not match, break out the loop
                if (match == false) {
                    break;
                }
            }
            
            // after the loop, check the flag
            if (match == true) {
                return i;
            }
        }
        
        return -1;
    }
}