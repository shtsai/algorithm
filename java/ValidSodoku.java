/**
 *  Determine if a Sudoku is valid
 */

// updated solution, combine three tests into a big test 
// avoid three nested for loop and improve performance
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        
        for (int i = 0; i < row; i++) {
            boolean[] rowValid = new boolean[10];
            boolean[] colValid = new boolean[10];
            boolean[] cubeValid = new boolean[10];
            
            for (int j=0; j<col; j++) {
                // test row
                int rowNum = board[i][j] - 48;     // -48 to convert ASCII to the corresponding int value
                if (rowNum >= 0 && rowNum <= 9) {  
                    if (rowValid[rowNum] == true) { return false;}
                    rowValid[rowNum] = true;
                }
                
                // test column
                int colNum = board[j][i] - 48;
                if (colNum >= 0 && colNum <= 9) {
                    if (colValid[colNum] == true) { return false;}
                    colValid[colNum] = true;
                }
                
                // test cube
                // use / and % to get the correct index
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                int cubeNum = board[RowIndex + j/3][ColIndex + j%3] - 48;
                if (cubeNum >= 0 && cubeNum <= 9) {
                    if (cubeValid[cubeNum] == true) { return false;}
                    cubeValid[cubeNum] = true;
                }
            }
        }

        return true;
    }
}


// original solution
// use three for loops to test three cases, less efficient

/*
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        
        for (int i = 0; i < row; i++) {
            boolean[] valid = new boolean[10];
            for (int j=0; j<col; j++) {
                int num = board[i][j] - 48;
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        for (int j = 0; j < col; j++) {
            boolean[] valid = new boolean[10];
            for (int i=0; i<row; i++) {
                int num = board[i][j] - 48;
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        for (int i = 0; i < row; i++) {
            boolean[] valid = new boolean[10];
            for (int j=0; j<col; j++) {
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);

                int num = board[RowIndex + j/3][ColIndex + j%3] - 48;
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        return true;
    }
}
*/

