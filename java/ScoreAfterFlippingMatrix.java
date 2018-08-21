/*
	We have a two dimensional matrix A where each value is 0 or 1.
	A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
	After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
	Return the highest possible score.

	Example 1:
	Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
	Output: 39
	Explanation:
	Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
	0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
	 
	Note:
	1 <= A.length <= 20
	1 <= A[0].length <= 20
	A[i][j] is 0 or 1.
 */

// Solution 2: Greedy
// Reference: https://leetcode.com/problems/score-after-flipping-matrix/solution/
// Time: O(mn)
// Space: O(1)
// 08/21/2018
class Solution {
    public int matrixScore(int[][] A) {
        int R = A.length, C = A[0].length;
        int ans = 0;
        for (int c = 0; c < C; ++c) {
            int col = 0;
            for (int r = 0; r < R; ++r)
                col += A[r][c] ^ A[r][0];
            ans += Math.max(col, R - col) * (1 << (C-1-c));
        }
        return ans;
    }
}

// Solution 1: Brute force
// Try flip every row and every col
// There are 2 ^ (m + n) possibilities
//
// Time: O(2 ^ (m + n) * mn)
// Space: O(m + n) - stack space
// 08/21/2018

class Solution {
    int max;
    public int matrixScore(int[][] A) {
        max = 0;
        search(A, 0, 0);
        return max;
    }
    
    private void search(int[][] A, int row, int col) {
        if (row == A.length && col == A[0].length) {
            max = Math.max(max, compute(A));
            return;
        } else if (row < A.length) {
            // don't flip
            search(A, row + 1, col);
            
            // flip row
            for (int i = 0; i < A[0].length; i++) {
                A[row][i] = A[row][i] ^ 1;
            }
            search(A, row + 1, col);
            // undo flip row
            for (int i = 0; i < A[0].length; i++) {
                A[row][i] = A[row][i] ^ 1;
            }
        } else {
            // don't flip
            search(A, row, col + 1);
            
            // flip col
            for (int i = 0; i < A.length; i++) {
                A[i][col] = A[i][col] ^ 1;
            }
            search(A, row, col + 1);
            // undo flip col
            for (int i = 0; i < A.length; i++) {
                A[i][col] = A[i][col] ^ 1;
            }
        }
    }
    
    private int compute(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            int cur = 0;
            for (int j = 0; j < A[0].length; j++) {
                cur = cur * 2 + A[i][j];
            }
            res += cur;
        }
        return res;
    }
}