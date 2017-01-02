/*
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 */

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
        	// build from right to left
        	// be careful with -1, shift everything to the left by 1 unit (0 = 'A', 25 = 'Z')
            sb.insert(0, (char) ('A'+ (n-1) % 26));
            n = (n-1) / 26;
        }
        return sb.toString();
    }
}