/*
	On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

	A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


	Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

	The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

	Example:
	Input: 3, 2, 0, 0
	Output: 0.0625
	Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
	From each of those positions, there are also two moves that will keep the knight on the board.
	The total probability the knight stays on the board is 0.0625.
	Note:
	N will be between 1 and 25.
	K will be between 0 and 100.
	The knight always initially starts on the board.
 */

// Solution 2: Memoization
// Save computed results so they can be used later.
//
// Time: O(N^2*K)
// Space: O(N^2)
// 07/08/2018

class Solution {
    private int[][] dirs = {{1, -2}, {2, -1}, {2, 1}, {1, 2},
                            {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};

    public double knightProbability(int N, int K, int r, int c) {
        double[][][] memo = new double[N][N][K+1];
        
        double total = Math.pow(8.0, K);
        double count = helper(r, c, N, K, memo);
        return count / total;
    }
    
    private double helper(int r, int c, int dimension, int moves, double[][][] memo) {
        if (!(isValid(r, dimension) && isValid(c, dimension) && moves >= 0)) {
            return 0.0;
        } 
        if (moves == 0) {  // base case
            return 1.0;
        }
        if (memo[r][c][moves] != 0.0) {  // read from memo
            return memo[r][c][moves];
        }
        double res = 0.0;
        for (int[] dir : dirs) {
            res += helper(r + dir[0], c + dir[1], dimension, moves - 1, memo);
        }
        memo[r][c][moves] = res;
        return res;
    }
    
    private boolean isValid(int x, int dimension) {
        return x >= 0 && x < dimension;
    }
}

// Solution 1: Recursion
// Try all possible moves, and count the number of valid moves.
// 
// Time: O(8^K)
// Space: O(k) - call stack
// 07/08/2018

class Solution {
    private int[][] dirs = {{1, -2}, {2, -1}, {2, 1}, {1, 2},
                            {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};

    public double knightProbability(int N, int K, int r, int c) {
        int total = (int) Math.pow(8, K);
        int count = helper(r, c, N, K);
        return (double) count / total;
    }
    
    private int helper(int r, int c, int dimension, int moves) {
        if (!(isValid(r, dimension) && isValid(c, dimension))) {
            return 0;
        } 
        if (moves == 0) {  // base case
            return 1;
        }
        
        int res = 0;
        for (int[] dir : dirs) {
            res += helper(r + dir[0], c + dir[1], dimension, moves - 1);
        }
        return res;
    }
    
    private boolean isValid(int x, int dimension) {
        return x >= 0 && x < dimension;
    }
}