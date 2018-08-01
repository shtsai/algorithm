/*
    A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

    Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
     

    Example 1:

    Input:
    matrix = [
      [1,2,3,4],
      [5,1,2,3],
      [9,5,1,2]
    ]
    Output: True
    Explanation:
    In the above grid, the diagonals are:
    "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
    In each diagonal all elements are the same, so the answer is True.
    Example 2:

    Input:
    matrix = [
      [1,2],
      [2,2]
    ]
    Output: False
    Explanation:
    The diagonal "[1, 2]" has different elements.

    Note:

    matrix will be a 2D array of integers.
    matrix will have a number of rows and columns in range [1, 20].
    matrix[i][j] will be integers in range [0, 99].

    Follow up:

    What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
    What if the matrix is so large that you can only load up a partial row into the memory at once?
 */

// Solution 2: Check top-left neighbor
// Time: O(mn)
// Space: O(1)
// 07/31/2018
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}

// Solution 1: Naive solution
// Time: O(mn)
// Space: O(1)
// 07/31/2018

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int len = Math.max(m, n);
        for (int i = 0; i <= m; i++) {
            Integer prev = null;
            int r = m - i, c = 0;
            while (r <= m && c <= n) {
                System.out.print(matrix[r][c] + "-");
                if (prev == null) {
                    prev = matrix[r][c];
                } else if (prev != matrix[r][c]) {
                    return false;
                }
                r++;
                c++;
            }
            System.out.println();
        }
        for (int j = 1; j <= n; j++) {
            Integer prev = null;
            int r = 0, c = j;
            while (r <= m && c <= n) {
                System.out.print(matrix[r][c] + "-");
                if (prev == null) {
                    prev = matrix[r][c];
                } else if (prev != matrix[r][c]) {
                    return false;
                }
                r++;
                c++;
            }
            System.out.println();
        }
        return true;
    }
}