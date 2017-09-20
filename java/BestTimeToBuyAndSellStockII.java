/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

// Solution 1:
// One pass, sum up all price increases
// Time: O(n), Space: O(1)
public class Solution {
    public int maxProfit(int[] prices) {

    	// This problem is actually easier than it looks.
    	// The idea is to greedily sum up all the prices increase.
    	// capture every uptick to maximize profit.

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}

// Solution 2:
// Find all local minimum and local maximum
// and sum up the differences
// Time: O(n), Space: O(1)
// reference: https://discuss.leetcode.com/topic/7436/java-o-n-solution-if-we-re-not-greedy
class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0, i = 0;
        while (i < prices.length) {
            while (i < prices.length-1 && prices[i] >= prices[i+1]) i++;
            int min = prices[i];    // local minimum
            i++;
            while (i < prices.length-1 && prices[i] < prices[i+1]) i++;
            sum += i >= prices.length ? 0 : prices[i] - min;
            i++;
        }
        return sum;
    }
}