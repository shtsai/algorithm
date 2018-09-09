/*
	We have a sorted set of digits D, a non-empty subset of {'1','2','3','4','5','6','7','8','9'}.  (Note that '0' is not included.)

	Now, we write numbers using these digits, using each digit as many times as we want.  For example, if D = {'1','3','5'}, we may write numbers such as '13', '551', '1351315'.

	Return the number of positive integers that can be written (using the digits of D) that are less than or equal to N.

	 

	Example 1:
	Input: D = ["1","3","5","7"], N = 100
	Output: 20
	Explanation: 
	The 20 numbers that can be written are:
	1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
	
	Example 2:
	Input: D = ["1","4","9"], N = 1000000000
	Output: 29523
	Explanation: 
	We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
	81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
	2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
	In total, this is 29523 integers that can be written using the digits of D.
	 
	Note:
	D is a subset of digits '1'-'9' in sorted order.
	1 <= N <= 10^9
 */

// Solution 3: DP
// Let dp[i] be the number of ways to write a valid number if N became N[i], N[i+1], .... 
// For example, if N = 2345, then dp[0] would be the number of valid numbers at most 2345, 
// dp[1] would be the ones at most 345, dp[2] would be the ones at most 45, 
// and dp[3] would be the ones at most 5.
// Then, by our reasoning above, 
// dp[i] = (number of d in D with d < S[i]) * ((D.length) ** (K-i-1)), plus dp[i+1] if S[i] is in D.
// 
// reference: https://leetcode.com/problems/numbers-at-most-n-given-digit-set/solution/
// Time: O(logn) - number of digits
// Space: O(logn)
// 09/09/2018

class Solution {
    public int atMostNGivenDigitSet(String[] D, int N) {
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K+1];
        dp[K] = 1;

        for (int i = K-1; i >= 0; --i) {
            // compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d: D) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(D.length, K-i-1);
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i+1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i);
        return dp[0];
    }
}

// Solution 2: 
// Use math to compute all numbers of length (digits - 1)
// Then count the numbers that are less than target with length `digit`
// Use a tree map to find the number of digits smaller than current digit.
//
// Time: O(dlogd + nlogd)
// Space: O(d)
// 09/09/2018
class Solution {
    int count;
    TreeMap<Integer, Integer> map;
    public int atMostNGivenDigitSet(String[] D, int N) {
        map = new TreeMap<>();
        for (int i = 0; i < D.length; i++) {
            map.put(Integer.parseInt(D[i]), i);
        }
        int digits = 0;
        int n = N;
        while (n > 0) {
            n /= 10;
            digits++;
        }
        count = 0;
        // count for numbers of (digits - 1) digits
        for (int i = 1; i < digits; i++) {
            count += (int) Math.pow(D.length, i);
        }
        search(N, D);
        return count;
    }
    
    private void search(int limit, String[] D) {
        String n = Integer.toString(limit);
        while (n.length() != 0) {
            int i = n.charAt(0) - '0';
            Integer key = map.lowerKey(i);  // find the number of digits smaller than i
            if (key != null) {	 // then the rest of `n - 1` digits can be any combination: d ^ (n - 1)
                count += (map.get(key) + 1) * (int) Math.pow(D.length, n.length() - 1);
            }
            if (map.containsKey(i)) {  // continue if `i` is in the map
                n = n.substring(1);
            } else {
                return;
            }
        }
        count++;
    }
}

// Solution 1:
// Use math to compute all numbers of length (digits - 1)
// Then do dfs to find all numbers of length `digits` and less than target
// Time: O(d ^ n) - d:# of avaiable digits, n:len
// Time Limit Exceeded
// Space: O(n) - stack
// 09/09/2018

class Solution {
    int count;
    public int atMostNGivenDigitSet(String[] D, int N) {
        int digits = 0;
        int n = N;
        while (n > 0) {
            n /= 10;
            digits++;
        }
        count = 0;
        // count for numbers of (digits - 1) digits
        for (int i = 1; i < digits; i++) {
            count += (int) Math.pow(D.length, i);
        }
        search(D, 0, 0, digits, N);
        return count;
    }
    
    private void search(String[] D, long value, int length, int digits, int limit) {
        if (length == digits) {
            count++;
            return;
        }
        for (int i = 0; i < D.length; i++) {
            if (value * 10 + Integer.parseInt(D[i]) <= limit) {
                search(D, value * 10 + Integer.parseInt(D[i]), length + 1, digits, limit);
            } else {
                break;
            }
        }
    }
}