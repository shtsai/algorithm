/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * 
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 */

// reference: https://discuss.leetcode.com/topic/3162/easy-to-understand-java-solution

class Solution {
    public String convert(String s, int numRows) {
        char[] chars = s.toCharArray();
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {   // create an stringbuilder for each row
            rows[i] = new StringBuilder();
        }
        
        int index = 0;
        while (index < chars.length) {
            for (int i = 0; i < numRows && index < chars.length; i++) { // vertically downward
                rows[i].append(chars[index]);
                index++;
            }
            for (int i = numRows-2; i > 0 && index < chars.length; i--) {   // diagonally upward
                rows[i].append(chars[index]);
                index++;
            } 
        }
        
        for (int i = 1; i < numRows; i++) {
            rows[0].append(rows[i]);        // merge all string builders
        }
        return rows[0].toString();
    }
}