/*
    Design a Tic-tac-toe game that is played between two players on a n x n grid.

    You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.
    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
    Example:
    Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

    TicTacToe toe = new TicTacToe(3);

    toe.move(0, 0, 1); -> Returns 0 (no one wins)
    |X| | |
    | | | |    // Player 1 makes a move at (0, 0).
    | | | |

    toe.move(0, 2, 2); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 2 makes a move at (0, 2).
    | | | |

    toe.move(2, 2, 1); -> Returns 0 (no one wins)
    |X| |O|
    | | | |    // Player 1 makes a move at (2, 2).
    | | |X|

    toe.move(1, 1, 2); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 2 makes a move at (1, 1).
    | | |X|

    toe.move(2, 0, 1); -> Returns 0 (no one wins)
    |X| |O|
    | |O| |    // Player 1 makes a move at (2, 0).
    |X| |X|

    toe.move(1, 0, 2); -> Returns 0 (no one wins)
    |X| |O|
    |O|O| |    // Player 2 makes a move at (1, 0).
    |X| |X|

    toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
    |X| |O|
    |O|O| |    // Player 1 makes a move at (2, 1).
    |X|X|X|
    Follow up:
    Could you do better than O(n2) per move() operation?
 */

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
 

// Solution 2:
// Keep one array for rows, one array for cols,
// one variable for diagonal and one for antidiagonal.
// player 1's move will increment count by 1, while
// player 2's move will decrement count by -1.
// Therefore, if any row, col, diagonal reaches N or -N,
// we know a player wins.
//
// Time: O(n)
// Space: O(n)
// 11/18/2017

class TicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antidiagonal;
    int N;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antidiagonal = 0;
        N = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int score = player == 1 ? 1 : -1;
        rows[row] += score;
        cols[col] += score;
        if (row == col) diagonal += score;
        if (row + col + 1 == N) antidiagonal += score;
        
        if (check(player)) return player;
        return 0;
    }
    
    public boolean check(int player) {
        int winning = player == 1 ? N : -N;
        if (diagonal == winning || antidiagonal == winning) return true;
        for (int i = 0; i < N; i++) {
            if (rows[i] == winning || cols[i] == winning) {
                return true;
            }
        }
        return false;
    }
}

// Solution 1: 
// Maintain of grid of the game states.
// After each move, scan through the grid and check if a player wins.
// Time: O(n^2)
// Space: O(n^2)

