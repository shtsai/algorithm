/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 * [
 *     [2],
 *    [3,4],
 *   [6,5,7],
 *  [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */

// Solution 2: O(n) space, 1D array, release unused memory
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
    
        if (triangle == null || triangle.size() == 0) return 0;
        
        for (int i = 0; i < triangle.size(); i++) {
            dp[i] = triangle.get(triangle.size()-1).get(i);
        }
        
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // add the smaller one of the two children
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0];
    }
}

// Solution 1: O(n2) space, 2D array
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];
        
        
        if (triangle == null || triangle.size() == 0) return 0;
        
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size()-1][i] = triangle.get(triangle.size()-1).get(i);
        }
        
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // add the smaller one of the two children
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0][0];
    }
}