/*
 *  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 */

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        
        List<List<String>> result = new ArrayList<>();
        int[] position = new int[n];      // the index indicates row, the value indicate col
        helper(0, n, position, result);   // use helper function to recursively solve smaller problem

        return result;
    }
    
    public void helper(int level, int n, int[] position, List<List<String>> result) {

        if (level == n) {    // we have found a valid placement
            List<String> list = buildBoard(position);  // build the board for this placement
            result.add(list);   //  and add it to the result list
            return;
        }
        
        for (int i = 0; i < n; i++) {  // try every column in this row
            if (isValid(level, i, position)) {   // if this position does not conflict with previous placement
                position[level] = i;   // mark the position
                helper(level+1, n, position, result);   // make recursive call
                position[level] = 0;   // unmark the position
            }
        }
    } 
    
    // this function checks if adding this new position will conflict with prior placement
    // make sure no two queens are on the same line 
    public boolean isValid(int row, int col, int[] position) {
        for (int i = 0; i < row; i++) {
            if (position[i] == col || (row-i) == Math.abs(col-position[i])){
                return false;
            }
        }
        return true;

    }
    
    // this function builds the board based on the placement
    public List<String> buildBoard(int[] position) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < position.length; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < position.length; col++) {
                if (position[row] == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        return list;
    }
}