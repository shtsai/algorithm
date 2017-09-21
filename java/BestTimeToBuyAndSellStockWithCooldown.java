/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * 
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * 
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */

// Solution 3: Dynamic programing with four variables
// Based on previous solution.
// Turns out we only need information from i-1 and i-2 day,
// which can be store in four variables.
// Time: O(n)
// Space: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int pre_sell = 0, sell = 0, pre_buy = 0, buy = -prices[0];
        
        for (int price : prices) {
            pre_buy = buy;
            buy = Math.max(pre_buy, pre_sell - price);
            pre_sell = sell;
            sell = Math.max(pre_sell, pre_buy + price);
        }
        
        return sell;
    }
}

// Solution 2: Dynamic Programming
// Use two DP arrays to store the state of each day
//
// buy[i] means before day i what is the maxProfit for any sequence end with buy.
// sell[i] means before day i what is the maxProfit for any sequence end with sell.
//
// The recurence can be defined as follow:
// buy[i] = max(sell[i-2]-price, buy[i-1])
// sell[i] = max(buy[i-1]+price, sell[i-1])
//
// buy[i] should refer to sell[i-2] because we have one-day cooldown,
// and we can only buy one after we sell.
//
// Time: O(n) - one pass
// Space: O(n) - 1D array
// reference: https://discuss.leetcode.com/topic/30421/share-my-thinking-process
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        
        buy[0] = -prices[0];    // initialize buy[0]
        for (int i = 1; i < len; i++) {
            // need to be careful with index here
            buy[i] = Math.max(buy[i-1], (i >= 2 ? sell[i-2]:0)-prices[i] );
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return sell[len-1];
    }
}

// Solution 1: DP solution with three dp array
// Good state graph
//
// buy[i]  = max(rest[i-1]-price, buy[i-1]) 
// sell[i] = max(buy[i-1]+price, sell[i-1])
// rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
//
// reference: https://discuss.leetcode.com/topic/30680/share-my-dp-solution-by-state-machine-thinking
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        int[] rest = new int[len];
        
        buy[0] = -prices[0];
        
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i-1], rest[i-1]-prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+prices[i]);
            rest[i] = Math.max(rest[i-1], sell[i-1]);
        }
        return sell[len-1];
    }
}