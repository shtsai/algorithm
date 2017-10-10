/*
    There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

    The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

    Note:
    All costs are positive integers.
 */

// Solution 1: Dynamic Programming
// Build a DP array that shows the minimum costs to print a house i
// with three different colors.
// To compute the minimum costs of painting with a particular color,
// scan through the costs array, at each house, we have three choices.
// add the current color cost to the minimum of the other two colors 
// in previous index.
// Finally, the result is the minimum of the last row.
// Time: O(n) - one pass
// Space: O(1) - Assuming we are allowed to modify input array
// 10/10/2017

class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        
        int n = costs.length-1;
        for (int i = 1; i <= n; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        
        return Math.min(Math.min(costs[n][0], costs[n][1]),costs[n][2]);
    }
}