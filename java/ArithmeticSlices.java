/*
	A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

	For example, these are arithmetic sequence:

	1, 3, 5, 7, 9
	7, 7, 7, 7
	3, -1, -5, -9
	The following sequence is not arithmetic.

	1, 1, 2, 5, 7

	A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

	A slice (P, Q) of array A is called arithmetic if the sequence:
	A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

	The function should return the number of arithmetic slices in the array A.


	Example:

	A = [1, 2, 3, 4]

	return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */

// Solution 3: 1D DP
// Define DP[i] = the number of arithmetic slices end with index `i`
// Recurrence relation = 
//	   1. i < 2 -> 0   // base case
//     2. A[i-1]-A[i] == A[i-2]-A[i-1]  ->  1 + DP[i-1]
//	   3. otherwise, = 0
// Time: O(n)
// Space: O(n)
// 07/14/2018
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int sum = 0;
        int[] memo = new int[A.length];
        Arrays.fill(memo, -1);
        for (int i = A.length - 1; i >= 0; i--) {
            sum += helper(memo, A, i);
        }
        return sum;
    }
    
    // returns number of arithmetic slices end with index `end`
    private int helper(int[] memo, int[] A, int end) {
        if (memo[end] != -1) {
        } else if (end < 2) {  // need at least three elements
            memo[end] = 0;
        } else if (A[end - 1] - A[end] == A[end - 2] - A[end - 1]) {   // find one pair
            memo[end] = 1 + helper(memo, A, end - 1);
        } else {
            memo[end] = 0;
        }
        return memo[end];
    }
}

// Solution 2: 
// Compute initial diff and count continous diff that match
//
// Time: O(n^2)
// Space: O(1)
// 07/14/2018
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0;
        for (int start = 0; start < A.length - 2; start++) {
            int diff = A[start + 1] - A[start];     // initial diff
            for (int end = start + 2; end < A.length; end++) {
                if (A[end] - A[end - 1] == diff) {  // check if diff of next pair matches initial diff
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }
}

// Solution 1: diff array + DP
// First compute difference array.
// Then do dynamic programming to find arithmetics slices
// Define dp[i][j] = whether slice(i, j) is arithmetic
// Recurrence relation:
//	   1. A[i] = A[j] && i + 1 == j -> true	   // base case, need at least three elements
//	   2. A[i] = A[j] -> dp[i+1][j] && dp[i][j-1]  // recurse on subproblem
//
// Time: O(n^2) 
// Space: O(n^2)
// 07/13/2018
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int[] diff = new int[A.length - 1];
        for (int i = 1; i < A.length; i++) {
            diff[i - 1] = A[i] - A[i - 1];
        }
        int count = 0;
        boolean[][] dp = new boolean[diff.length][diff.length];
        for (int i = 1; i < diff.length; i++) {
            for (int j = 0; i + j < diff.length; j++) {
                int r = j, c = i + j;
                if (diff[r] == diff[c]) {
                    if (r + 1 == c) {
                        dp[r][c] = true;
                        count++;
                    } else if (dp[r + 1][c] && dp[r][c - 1]) {
                        dp[r][c] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}