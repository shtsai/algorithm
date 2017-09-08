/**
 *  Determine if a Sudoku is valid
 */

// Solution 2 updated version
// combine three tests into a big test 
// use boolean array to indicate whether or not a number has been used
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
                int rowNum = board[i][j] - '0';     // -48 to convert ASCII to the corresponding int value
                if (rowNum >= 0 && rowNum <= 9) {  
                    if (rowValid[rowNum] == true) { return false;}
                    rowValid[rowNum] = true;
                }
                
                // test column
                int colNum = board[j][i] - '0';
                if (colNum >= 0 && colNum <= 9) {
                    if (colValid[colNum] == true) { return false;}
                    colValid[colNum] = true;
                }
                
                // test cube
                // use / and % to get the correct index
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                int cubeNum = board[RowIndex + j/3][ColIndex + j%3] - '0';
                if (cubeNum >= 0 && cubeNum <= 9) {
                    if (cubeValid[cubeNum] == true) { return false;}
                    cubeValid[cubeNum] = true;
                }
            }
        }

        return true;
    }
}


// Solution 2:
// use three for loops to test three cases

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        
        for (int i = 0; i < row; i++) {
            boolean[] valid = new boolean[10];
            for (int j=0; j<col; j++) {
                int num = board[i][j] - '0';
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        for (int j = 0; j < col; j++) {
            boolean[] valid = new boolean[10];
            for (int i=0; i<row; i++) {
                int num = board[i][j] - '0';
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        // be careful with the indexing here
        for (int i = 0; i < row; i++) {
            boolean[] valid = new boolean[10];
            for (int j=0; j<col; j++) {
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);

                int num = board[RowIndex + j/3][ColIndex + j%3] - '0';
                if (num < 0) {continue;};
                if (valid[num] == true) { return false;}
                valid[num] = true;
            }
        }
        
        return true;
    }
}

// Solution 1:
// naive implementation
// check for every row, col and square
// use set, more space
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // check rows
        for (int i = 0; i < board.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j <board[0].length; j++) {
                char cell = board[i][j];
                if (cell == '.') {
                    continue;
                } else if (cell >= '1' && cell <= '9' && !set.contains(cell)) {
                    set.add(cell);
                } else {
                    return false;   
                }
            }
        }
        // check cols
        for (int j = 0; j < board[0].length; j++) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < board.length; i++) {
                char cell = board[i][j];
                if (cell == '.') {
                    continue;
                } else if (cell >= '1' && cell <= '9' && !set.contains(cell)) {
                    set.add(cell);
                } else {
                    return false;
                }
            }
        }
        // check squares
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {
                Set<Character> set = new HashSet<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        char cell = board[i+k][j+l];
                        if (cell == '.') continue;
                        else if (cell >= '1' && cell <= '9' && !set.contains(cell)) {
                            set.add(cell);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}