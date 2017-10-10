/*
    There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

    The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

    Note:
    All costs are positive integers.

    Follow up:
    Could you solve it in O(nk) runtime?
 */

// Solution 2 version 2:
// More consice version
// Update dp array, then update min
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        
        int n = costs.length - 1, colors = costs[0].length;
        int min1 = -1, min2 = -1;
        for (int i = 0; i <= n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; 
            min2 = -1;
            
            for (int j = 0; j < colors; j++) {
                // update dp array
                if (j != last1) {    
                    costs[i][j] += (last1 == -1) ? 0 : costs[i-1][last1];
                } else {
                    costs[i][j] += (last2 == -1) ? 0 : costs[i-1][last2];
                }
                
                // update mins
                if (min1 == -1 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 == -1 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        
        return costs[n][min1];
    }
}

// Solution 2: improved version of solution 1
// At each house i with color j, it is actually not necessary to compare
// with all k-1 way of paintings of the previous house.
// Instead, we only need to know the minimum and second minimum cost and  
// their corresponding colors of the previous houses.
// If color we have is not the same as the color used in previous minimum,
// we can safely use this color by adding previous minimum to it.
// If they happen to be the same color, we can still go with the second min.
// In this way, we reduce O(k^2) pair-wise comparison to O(k) min searching.
//
// Time: O(nk) - O(k) for min searching + O(k) for updating dp array
//               for a total of n iterations
// Space: O(1) - Assuming we can modify input array
// 10/10/2017

class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        
        int n = costs.length - 1, colors = costs[0].length;
        for (int i = 1; i <= n; i++) {
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int color1 = -1, color2 = -1; 
            for (int j = 0; j < colors; j++) {
                if (costs[i-1][j] < min1) {
                    min2 = min1;
                    color2 = color1;
                    min1 = costs[i-1][j];
                    color1 = j;
                } else if (costs[i-1][j] < min2) {
                    min2 = costs[i-1][j];
                    color2 = j;
                }
            }
            for (int j = 0; j < colors; j++) {
                if (j != color1) {
                    costs[i][j] += min1;
                } else {
                    costs[i][j] += min2;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < colors; j++) {
            res = Math.min(res, costs[n][j]);
        }
        return res;
    }
}

// Solution 1: Generalized solution of paint house I
// Build a DP array of size nk (or just use the input array)
// Each cell [i][j] represents the min cost of painting houses up to house i
// with house i being painted with color j.
// Therefore, at each cell, we are using color j, and we choose the min cost 
// among (k-1) ways of painting the previous house.
// Time: O(nk^2) - for each color, we consider the other k-1 colors, and there
//                 are k colors and n houses in total
// Space: O(1) - assuming we can manipulate input array
// 10/10/2017

class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        
        int n = costs.length - 1, colors = costs[0].length;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < colors; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = 1; k < colors; k++) {
                    temp = Math.min(temp, costs[i][j] + costs[i-1][(j+k)%colors]);
                }
                costs[i][j] = temp;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < colors; j++) {
            res = Math.min(res, costs[n][j]);
        }
        return res;
    }
}