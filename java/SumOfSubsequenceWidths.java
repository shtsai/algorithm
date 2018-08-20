/*
    Given an array of integers A, consider all non-empty subsequences of A.
    For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
    Return the sum of the widths of all subsequences of A.
    As the answer may be very large, return the answer modulo 10^9 + 7.

    Example 1:
    Input: [2,1,3]
    Output: 6
    Explanation:
    Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
    The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
    The sum of these widths is 6.

    Note:
    1 <= A.length <= 20000
    1 <= A[i] <= 20000
 */

// Solution 2:
// For A[i]:
// There are i smaller numbers,
// so there are 2 ^ i sequences in which A[i] is maximum.
// we should do res += A[i] * (2 ^ i)
// There are n - i - 1 bigger numbers,
// so there are 2 ^ (n - i - 1) sequences in which A[i] is minimum.
// we should do res -= A[i] * 2 ^ (n - i - 1)
//
// Time: O(nlogn) - sort
// Space: O(n)
// 08/19/2018
class Solution {
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        long res = 0;
        long mod = (long) 1e9 + 7;

        // pre-compute all powers
        long[] pow = new long[A.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }

        for (int i = 0; i < A.length; i++) {
            res = (res + A[i] * pow[i] - A[i] * pow[A.length - i - 1]) % mod;
        }

        // in Java, mod on negative number will still get a negative number
        return (int) ((res + mod) % mod);
    }
}


// Solution 1: Brute force
// Find all subsequence and compute sum of diffs
//
// Time: O(2^n) - Time limit exceeded
// Space: O(1)
// 08/18/2018

class Solution {
    long res;
    public int sumSubseqWidths(int[] A) {
        res = 0;
        search(A, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return (int) res;
    }

    private void search(int[] A, int i, int min, int max) {
        if (i == A.length) {
            if (min != Integer.MAX_VALUE && max != Integer.MIN_VALUE) {
                res = (res + (max - min)) % ((int) Math.pow(10, 9) + 7);
            }
            return;
        }
        search(A, i + 1, min, max);    // dont add
        min = Math.min(min, A[i]);
        max = Math.max(max, A[i]);
        search(A, i + 1, min, max);    // add
    }
}
