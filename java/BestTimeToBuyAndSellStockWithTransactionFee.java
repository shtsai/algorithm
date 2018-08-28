/*
	Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

	You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

	Return the maximum profit you can make.

	Example 1:
	Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
	Output: 8
	Explanation: The maximum profit can be achieved by:
	Buying at prices[0] = 1
	Selling at prices[3] = 8
	Buying at prices[4] = 4
	Selling at prices[5] = 9
	The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
	Note:

	0 < prices.length <= 50000.
	0 < prices[i] < 50000.
	0 <= fee < 50000.
 */

// Generalized solution to buy and sell stocks
// reference: https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems

// Solution 2: Dynamic programming with dp arrays
// Time: O(n)
// Space: O(n)
// 08/28/2018
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length == 0) {
            return 0;
        }
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
        }
        return sell[prices.length - 1];
    }
}


// Solution 1: Dynamic programming
// For each day, there are two possible states:
//		1: with 0 stock in hand (wait, or sell)
//		2: with 1 stock in hand (wait, or buy)
// We can use DP to keep track of the maximum profits for each
// day with these two states.
// Since we only need to consider previous day for the calculation
// of current day, we can use two variables buy and sell.
//
// Time: O(n) - number of days
// Space: O(1) - only two variables
// 10/24/2017

class Solution {
    public int maxProfit(int[] prices, int fee) {
        long sell = 0, buy = Integer.MIN_VALUE;
        
        for (int price : prices) {
            long old_sell = sell;
            sell = Math.max(sell, buy + price - fee); // wait or sell
            buy = Math.max(buy, old_sell - price);    // wait or buy
        }
        
        return (int) sell;
    }
}