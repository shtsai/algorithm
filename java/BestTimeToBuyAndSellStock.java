/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 * design an algorithm to find the maximum profit.
 */

// Solution 3: Kadane's Algorithm
// Compare cummulative daily differences, reset to 0 when difference is negative
// Use a variable max to keep track of max difference
// Time: O(n)
// Space: O(1)
public class Solution {
    public int maxProfit(int[] prices) {
        // dynamic programming
        int maxCur = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);  // after adding the price change, is maxCur > 0?
            max = Math.max(max, maxCur);
        }
        return max;
    }
}

// Solution 2 improved version 2:
// We only need to use two variables to keep track of the max profit and min price.
// Time: O(n)
// Space: O(1)
// 10/24/2017

class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE, sell = 0;      
        for (int price : prices) {
            sell = Math.max(sell, price-buy);  // determine sell or not
            buy = Math.min(buy, price);    // find lowest prices
        }
        return sell;
    }
}

// Solution 2: Find min buy price at each day
// Time: O(n)
// Space: O(n)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] lowest = new int[prices.length];
        int res = 0;
        lowest[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            lowest[i] = Math.min(lowest[i-1], prices[i]);
            res = Math.max(prices[i]-lowest[i], res);
        }
        return res;
    }
}

// Solution 1: Brute force
// Check every valid buy and sell
// Time: O(n^2) 
// Space: O(1)
public class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = max = prices[j] - prices[i];
                if (profit > max) {
                    max = profit;
                }
            }
        }
        return max;
    }
}
