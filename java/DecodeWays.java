/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */

// Solution 2: Dynamic programming
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int one = Integer.valueOf(s.substring(i-1, i));      // one-digit encoding
            int two = Integer.valueOf(s.substring(i-2, i));      // two-digit encoding
            if (0 < one && one <= 9) dp[i] += dp[i-1];
            if (10 <= two && two <= 26) dp[i] += dp[i-2];
        }
        return dp[len];
    }
}

// Solution 1: recursion
// Time limit exceeded
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return helper(s);
    }
    
    public int helper(String s) {
        int len = s.length(), res = 0;
        if (s == null || len == 0) return 1;    // reaches the end, find a way of decoding
        if (s.charAt(0) == '0') return 0;   // message cannot start with 0
        
        for (int i = 1; i <= s.length(); i++) {
            int v = Integer.valueOf(s.substring(0, i));
            if (v > 26) break;      // no mapping exist
            if (v > 0 && v <= 26) {
                res += helper(s.substring(i, len));     // recursively solve subproblems
            }
                
        }
        return res;
    }
}