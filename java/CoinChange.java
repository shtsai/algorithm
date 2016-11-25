/*
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1. 
 */

// Dynamic Programming
public class Solution {
    public int coinChange(int[] coins, int amount) {
	// dp array, store the minimum number of coin change for the current amount (current index)
	int[] change = new int[amount + 1];
	change[0] = 0; // 0 requires 0 coin change
	for (int i = 1; i <= amount; i++) {
	    change[i] = -1;  // initialize all elements of the array to -1
	}

	for (int i = 1; i <= amount; i++) {
	    int temp = Integer.MAX_VALUE; // stores the minimun value among all the possible changes
	    for (int coin : coins) {  // try all coins
		if (i - coin >= 0 && change[i-coin] != -1) {
		    // if the coin is smaller than the amount, and the remaining amount is changable
		    temp = Math.min(temp, 1 + change[i-coin]);
		}
	    }
	    if (temp != Integer.MAX_VALUE) { // if there exists an valid change
		change[i] = temp;            // store its value in the dp array
	    }
	}
	return change[amount];
    }
}
