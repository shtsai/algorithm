/*
    Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

    Substrings that occur multiple times are counted the number of times they occur.

    Example 1:
    Input: "00110011"
    Output: 6
    Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

    Notice that some of these substrings repeat and are counted the number of times they occur.

    Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
    Example 2:
    Input: "10101"
    Output: 4
    Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
    Note:

    s.length will be between 1 and 50,000.
    s will only consist of "0" or "1" characters.
 */

// Solution 1: Two pointers
// One pointer points at the start of a range, the other pointer scans
// the remaining of the array. 
// Increment counter for one and zero.
// If we find non-consecutive occurrence, break the current iteration,
// and move left pointer to the right by 1.
//
// Time: O(n^2)
// Space: O(1)
// 10/14/2017

class Solution {
    public int countBinarySubstrings(String s) {
        int res = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            int one = 0, zero = 0; 
            if (s.charAt(i) == '1') {
                one++;
            } else {
                zero++;
            }
            for (int j = i+1; j < len; j++) {
                if (s.charAt(j) == '1') {
                    if (s.charAt(j) == (s.charAt(j-1)) || one == 0) {
                        one++;
                    } else {    // find non-consecutive occurrence
                        break;
                    }
                } else { 
                    if (s.charAt(j) == (s.charAt(j-1))  || zero == 0) {
                        zero++;   
                    } else {    // find non-consecutive occurrence
                        break;
                    }
                }
                if (one == zero) {  // find valid
                    res++;
                }
            }
        }
        return res;
    }
}