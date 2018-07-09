/*
    Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

    Example 1:
    Input:
    [[1,1,1],
     [1,0,1],
     [1,1,1]]
    Output:
    [[0, 0, 0],
     [0, 0, 0],
     [0, 0, 0]]
    Explanation:
    For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
    For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
    For the point (1,1): floor(8/9) = floor(0.88888889) = 0
    Note:
    The value in the given matrix is in the range of [0, 255].
    The length and width of the given matrix are in the range of [1, 150].

 */

// Solution 1: Nested for loop
// Time: O(8n^2)
// Space: O(1)
// 07/08/2018

class Solution {
    final int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                    {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int[][] imageSmoother(int[][] M) {
        int[][] newM = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                int total = M[i][j];
                int count = 1;
                for (int[] dir : dirs) {
                    int r = i + dir[0];
                    int c = j + dir[1];
                    if (r >= 0 && r < M.length && c >= 0 && c < M[0].length) {
                        total += M[r][c];
                        count++;
                    }
                }
                newM[i][j] = total / count;
            }
        }
        return newM;
    }
}