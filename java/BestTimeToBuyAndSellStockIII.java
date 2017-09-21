/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */

// Solution 3:
// Generalized solution for k transactions
// See BestTimeToBuyAndSellStockIV.java

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
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1-prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
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