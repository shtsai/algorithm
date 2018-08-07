/*
    An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

    If the character read is a letter, that letter is written onto the tape.
    If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
    Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

     

    Example 1:

    Input: S = "leet2code3", K = 10
    Output: "o"
    Explanation: 
    The decoded string is "leetleetcodeleetleetcodeleetleetcode".
    The 10th letter in the string is "o".
    Example 2:

    Input: S = "ha22", K = 5
    Output: "h"
    Explanation: 
    The decoded string is "hahahaha".  The 5th letter is "h".
    Example 3:

    Input: S = "a2345678999999999999999", K = 1
    Output: "a"
    Explanation: 
    The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
     

    Note:

    1. 2 <= S.length <= 100
    2. S will only contain lowercase letters and digits 2 through 9.
    3. S starts with a letter.
    4. 1 <= K <= 10^9
    5. The decoded string is guaranteed to have less than 2^63 letters.
 */

// Solution 2: 
// First pass: forward count length
// Second pass: backward reduce length and update K in each iteration
// Time: O(n)
// Space: O(1)
// 08/07/2018
class Solution {
    public String decodeAtIndex(String S, int K) {
        long size = 0;
        for (char c : S.toCharArray()) {
            if (Character.isDigit(c)) {
                size *= c - '0';
            } else {
                size++;
            }
        }
        
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c)) {
                return Character.toString(c);
            }
            
            if (Character.isLetter(c)) {
                size--;
            } else {
                size /= c - '0';
            }
        }
        throw null;
    }
}


// Solution 1: Naive solution
// Decode the entire string and find index K
// Time limit exceeded
// 08/04/2018
class Solution {
    public String decodeAtIndex(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (sb.length() < K) {
            char c = S.charAt(i++);
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                StringBuilder newSb = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    newSb.append(sb.toString());
                }
                sb = newSb;
            } else {
                sb.append(c);
            }
        }
        return Character.toString(sb.charAt(K - 1));
    }
}