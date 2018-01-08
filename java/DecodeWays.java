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

// Solution 4: Bottom-up Dynamic programming
// Each DP[i] represents # of decode ways from index 0 to index i.
// Recurence relation:
//     1. if s[i] is a valid integer, 
//        we can append it to previous result of dp[i-1]
//     2. if s[i-1: i] is a valid integer,
//        we can append it to the second previous result dp[i-2]
//
// Time: O(n) - one pass
// Space: O(n) - dp array
// 01/08/2018
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

// Solution 3: Recursion with memoization, top down DP [suffix]
// Same idea as solution 2, but use memo array to store intermediate results.
// Time: O(n) - n problems in total
// Space: O(n) - memo array
// 01/08/2018
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return search(s, 0, memo);
    }
    
    public int search(String s, int index, int[] memo) {
        if (index == s.length()) {
            return 1;
        }
        if (memo[index] != -1) return memo[index];
        memo[index] = 0;
        if (s.charAt(index) != '0') {
            memo[index] += search(s, index+1, memo);
        }
        if (index < s.length()-1) {
            int two = Integer.parseInt(s.substring(index, index + 2));
            if (two >= 10 && two <= 26) {
                memo[index] += search(s, index + 2, memo);
            }
        }
        return memo[index];
    }
}

// version 2: Suffix DP
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

// Solution 2: Recursion
// Break problems into smaller subproblems
// decode[i:] = (i is valid) * decode[i+1:] + ([i:i+1] is valid) * decode[i+2:]
// Time: O(2^n) - time limit exceeded
// Space: O(n) - call stack
// 01/08/2018
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        return search(s, 0);
    }
    
    public int search(String s, int index) {
        if (index == s.length()) {
            return 1;
        }
        int res = 0;
        if (s.charAt(index) != '0') {
            res += search(s, index+1);
        }
        if (index < s.length()-1) {
            int two = Integer.parseInt(s.substring(index, index + 2));
            if (two >= 10 && two <= 26) {
                res += search(s, index + 2);
            }
        }
        return res;
    }
}

// Solution 1: Brute force, DFS search
// Time: O(2^n) - Time limit exceeded
// Space: O(n) - call stack
class Solution {
    int count;
    public int numDecodings(String s) {
        if (s.length() == 0 ) return 0;
        count = 0;
        search(s, 0);
        return count;
    }
    public void search(String s, int index) {
        if (index == s.length()) {
            count++;
            return;
        }
        if (s.charAt(index) != '0') {
            search(s, index+1);
        }
        if (index < s.length()-1) {
            int two = Integer.parseInt(s.substring(index, index + 2));
            if (two >= 10 && two <= 26) {
                search(s, index + 2);
            }
        }
    }
}