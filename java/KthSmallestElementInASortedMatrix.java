/*
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example:
 * 
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 */

// Solution 2:
// Binary search
// guess the mid number, count the numbers that are less than the mid
// adjust range according to the result
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n-1][n-1]+1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0, j = n-1;         // count the number of elements that are larger than mid
            for (int i = 0; i < n; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;      // if matrix[i][j] > mid, then matrix[i+1][j] > mid, because columns are sorted
                count += (j + 1);
            }
            if (count < k) low = mid + 1;  // mid is smaller than Kth smallest element
            else high = mid;
        }
        
        return low;
    }
}

// Solution 1:
// use priority queue
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        int[] index = new int[matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
            q.offer(new Tuple(matrix[0][i], 0, i));    // add first row
        }
        
        while (k > 1) {
            // remove the smallest element in the priority queue
            // replace it with the next large element in its column (if exist)
            Tuple t = q.poll();
            if (t.row < matrix.length-1) q.offer(new Tuple(matrix[t.row+1][t.col], t.row+1, t.col)); 
            k--;
        }
        
        return q.poll().num;
    }
    

}

class Tuple implements Comparable<Tuple> {
    int num;
    int row;
    int col;
    
    public Tuple (int number, int row_num, int col_num) {
        this.num = number;
        this.row = row_num;
        this.col = col_num;
    }
    
    public int compareTo(Tuple other) {
        return this.num - other.num;
    }
}