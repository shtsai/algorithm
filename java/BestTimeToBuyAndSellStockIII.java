/*
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 */

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
            left[i] = Math.max(left[i-1], prices[i]-min);
        }
        
        // calculate the DP from the right to left
        right[prices.length-1] = 0;
        int max = prices[prices.length-1];
        for (int i = prices.length-2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
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