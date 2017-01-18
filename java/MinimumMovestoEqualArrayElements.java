/*
 * Given a non-empty integer array of size n, 
 * find the minimum number of moves required to make all array elements equal, 
 * where a move is incrementing n - 1 elements by 1.
 */

// incrementing n - 1 elements by 1 is the same as subtracting an element by 1
// thus, we just need to make all elements to be the min
public class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {  // find the min
            if (num < min) {
                min = num;
            }
        }
        int count = 0;
        for (int num : nums) {
            count += (num - min);  // sum up all the moves
        }
        
        return count;
    }
}