/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */

// Solution 3:
// Generalized solution for k transactions
// Time: O(nk)
// Space: O(nk)
// 08/25/2018
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] buys = new int[prices.length + 1][3];
        int[][] sells = new int[prices.length + 1][3];
        for (int k = 0; k < 3; k++) {
            buys[0][k] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int k = 2; k > 0; k--) {
                sells[i][k] = Math.max(sells[i - 1][k], prices[i] + buys[i - 1][k]);
                buys[i][k] = Math.max(buys[i - 1][k], sells[i - 1][k - 1] - prices[i]);
            }
        }
        return sells[prices.length - 1][2];
    }
}


// Solution 2:
// Dynamic programming, without extra memory
// Use four variables to represent four states
//
// buy1: we buy the first stock, so we subtract its price from the money we have
// sell1: we sell the stock, add its prices to our money
// buy2: we buy the second stock, on the top of our first transaction
// sell2: we sell the second stock, add its value to our profit
//
// Finally, the result is sell2.
// This solution is looking for the lowest prices to buy and 
// highest prices to sell.
// Time: O(n) - one pass
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/39751/my-explanation-for-o-n-solution

// version 1: DP array
// Use two 2D array to keep track of all states
// Time: O(n)
// Space: O(n)
// 10/24/2017

class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] buy = new int[n][2];
        int[][] sell = new int[n][2];
        buy[0][0] = -prices[0];  // buy[i][0] = first buy
        buy[0][1] = -prices[0];  // buy[i][1] = second buy
        
        for (int i = 1; i < n; i++) {
            sell[i][1] = Math.max(sell[i-1][1], buy[i-1][1] + prices[i]);
            buy[i][1] = Math.max(buy[i-1][1], sell[i-1][0] - prices[i]);
            sell[i][0] = Math.max(sell[i-1][0], buy[i-1][0] + prices[i]);
            buy[i][0] = Math.max(buy[i-1][0], -prices[i]);
        }
        
        return sell[n-1][1];
    }
}

// version 2: DP variables
// Use two variables to keep track of all states
// Time: O(n)
// Space: O(1)
// 10/24/2017

class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }
        
        return sell2;
    }
}


// Solution 1: Dynamic programming
// Similiar to BestTimeToBuyAndSellStock I
// We find the max profits obtainable from left to right and from right to left,
// and combine two results to find the overall maximum.
// Time: O(n)- three-pass
// Space: O(n) - two arrays
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 0) { return 0;}
        
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        
        // calculate the DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            // record the max profit obtained when buy on min day and sell on i-th day 
            left[i] = Math.max(left[i-1], prices[i]-min);
        }
        
        // calculate the DP from the right to left
        right[prices.length-1] = 0;
        int max = prices[prices.length-1];
        for (int i = prices.length-2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            // record the max profit obtained when buy on i-th day and sell on max day
            right[i] = Math.max(right[i+1], max-prices[i]);
        }
        
        // Find a middle point where the sum of left and right are maximized
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum = Math.max(sum, left[i] + right[i]);
        }
        return sum;
    }
}