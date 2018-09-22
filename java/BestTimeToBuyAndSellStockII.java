/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

// Solution 3: Dynamic Programming
// For each day, there are two possible states:
//      1: sell (wait with 0 stock or sell the current stock)
//      2: buy (hold the current stock or buy a new stock)
// We can use two variables to hold these two states.
//
// At each day, we consider all options and keep track of the max values.
//      sell:   1. wait (previous sell value)
//              2. sell (sell the stock we bought on i-1 day + price)
//      buy:    1. hold (previous buy value)
//              2. buy (buy ith stock using the money we earns from i-1 day)
//
// Time: O(n)
// Space: O(1)
// 10/24/2017

class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, sell = 0;
        for (int price : prices) {
            int presell = sell;
            sell = Math.max(sell, buy + price); // wait or sell
            buy = Math.max(buy, presell - price);
        }
        return sell;
    }
}

// Solution 2:
// One pass, sum up all price increases
// The idea is to greedily sum up all the prices increase.
// capture every uptick to maximize profit.
// Time: O(n)
// Space: O(1)
public class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}

// Solution 1:
// Find all local minimum and local maximum
// and sum up the differences
// Time: O(n)
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/7436/java-o-n-solution-if-we-re-not-greedy
// 09/22/2018
class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0, i = 0;
        while (i < prices.length) {
            // find local minimum
            while (i + 1 < prices.length && prices[i] >= prices[i+1]) {
                i++;
            }
            int min = prices[i];    
            i++;
            
            // find local maximum
            while (i + 1 < prices.length && prices[i] < prices[i+1]) {
                i++;
            }
            if (i < prices.length) {
                sum += prices[i] - min;
            }
            i++;
        }
        return sum;
    }
}