/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example,
 * Given the following matrix:
 *
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */

// Solution 2: while loop
// Time: O(mn)
// Space: O(1)
// 08/02/2018
class Solution {
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int dirIndex = 0;
        int top = 0, bottom = matrix.length;
        int left = 0, right = matrix[0].length;
        int i = 0, j = 0;
        while (true) {
            res.add(matrix[i][j]);
            int newi = i + dirs[dirIndex][0];
            int newj = j + dirs[dirIndex][1];
            if (newi < top || newi >= bottom || newj < left || newj >= right) {  // end of row/column
                switch (dirIndex) {
                    case 0:
                        top++;
                        break;
                    case 1:
                        right--;
                        break;
                    case 2:
                        bottom--;
                        break;
                    case 3:
                        left++;
                        break;
                } 
                if (top == bottom || left == right) {
                    break;
                }
                dirIndex = (dirIndex + 1) % 4;
                i += dirs[dirIndex][0];
                j += dirs[dirIndex][1];
            } else {
                i = newi;
                j = newj;
            }
        }
        return res;
    }
}


// Solution 1
// traverse the matrix in spiral order and store the elements on the way
// Use four variables to store the states.
// Time: O(mn)
// Space: O(1)
// 09/23/2018
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int rs = 0, re = matrix.length - 1;
        int cs = 0, ce = matrix[0].length - 1;
        while (rs <= re && cs <= ce) {
            addRight(matrix, res, rs, cs, ce);
            rs++;
            if (rs > re) {
                break;
            }
            addDown(matrix, res, rs, re, ce);
            ce--;
            if (ce < cs) {
                break;
            }
            addLeft(matrix, res, re, ce, cs);
            re--;
            if (re < rs) {
                break;
            }
            addUp(matrix, res, re, rs, cs);
            cs++;
            if (cs > ce) {
                break;
            }
        }
        return res;
    }
    
    private void addRight(int[][] matrix, List<Integer> res, int rs, int cs, int ce) {
        while (cs <= ce) {
            res.add(matrix[rs][cs]);
            cs++;
        }
    }
    
    private void addDown(int[][] matrix, List<Integer> res, int rs, int re, int ce) {
        while (rs <= re) {
            res.add(matrix[rs][ce]);
            rs++;
        }
    }
    
    private void addLeft(int[][] matrix, List<Integer> res, int re, int ce, int cs) {
        while (ce >= cs) {
            res.add(matrix[re][ce]);
            ce--;
        }
    }
    
    private void addUp(int[][] matrix, List<Integer> res, int re, int rs, int cs) {
        while (re >= rs) {
            res.add(matrix[re][cs]);
            re--;
        }
    }
}