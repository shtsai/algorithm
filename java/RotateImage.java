/*
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 */

// solution 2:
// in-place
// first flip the image horizontally, then flip it diagonally
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        // flip vertically
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-1-j];
                matrix[i][matrix[0].length-1-j] = temp;
            }
        }
        
        // flip diagonally
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length-i; j++) { 
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][matrix[0].length-1-i];
                matrix[matrix.length-1-j][matrix[0].length-1-i] = temp;
            }
        }
        
        return;
    }
}

// solution 1:
// first make a copy of the matrix, then rotate
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                matrix[j][copy[0].length-1-i] = copy[i][j];
            }
        }
        
        return;
    }
}
