/*
	Validate if a given string is numeric.

	Some examples:
	"0" => true
	" 0.1 " => true
	"abc" => false
	"1 a" => false
	"2e10" => true

	Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

	Update (2015-02-10):
	The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
 */

// Solution 1: 
// Set a number of flags
// Time: O(n)
// Space: O(1)
// 08/23/2018

class Solution {
    public boolean isNumber(String s) {
        boolean hasNums = false;
        boolean hasPoint = false;
        boolean hasE = false;
        boolean hasNumsAfterE = false;
        
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (hasE) {
                    hasNumsAfterE = true;
                } else {
                    hasNums = true;
                }
            } else if (c == '.') {
                if (hasPoint || hasE) {
                    return false;
                }
                hasPoint = true;
            } else if (c == 'e') {
                if (hasE) {
                    return false;
                } 
                hasE = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return (hasNums && (!hasE || hasNumsAfterE));
    }
}