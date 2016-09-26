/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 * design an algorithm to find the maximum profit.
 */

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

        /*
        // brute force
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
        */
    }
}