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
// Each DP[i] represents # of decode ways from index 0 to index i.
// Recurence relation:
//     1. if s[i] is a valid integer, 
//        we can append it to previous result of dp[i-1]
//     2. if s[i-1: i] is a valid integer,
//        we can append it to the second previous result dp[i-2]
//
// Time: O(n) - one pass
// Space: O(n) - dp array
// version 3: cleaner
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

// version 2: Recursive approach
// 11/18/2017
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        return decode(s, dp, s.length());
    }
    public int decode(String s, int[] dp, int i) {
        if (dp[i] != -1) return dp[i];
        int one = Integer.parseInt(s.substring(i-1, i));
        int two = Integer.parseInt(s.substring(i-2, i));
        int res = 0;
        if (one > 0 && one <= 9) res += decode(s, dp, i-1);
        if (two >= 10 && two <= 26) res += decode(s, dp, i-2);
        dp[i] = res;
        return res;
    }
}

// version 1:
// 11/18/2017
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        if (s.charAt(0) != '0') dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i-1];
            }
            if (s.charAt(i-1) != '0') {
                int two = Integer.parseInt(s.substring(i-1, i+1));
                if (two > 0 && two <= 26) {
                    int preTwo = i-2 >= 0 ? dp[i-2] : 1;
                    dp[i] += preTwo;
                }       
            }
        }
        return dp[s.length()-1];
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