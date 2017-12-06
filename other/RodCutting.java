import java.util.*;

/*
 * Rodcutting problem, each length is allowed at most once.
 * Find maximum value of cuttings.
 */

class RodCutting {
    public static void main(String args[]) {
	int[] prices = {0, 2, 5, 7, 8, 10};
	int best = cut(prices, 4);
	System.out.println(best);
    }
    
    public static int cut(int[] prices, int len) {
	int[][] memo = new int[len+1][prices.length+1];
	for (int i = 0; i < len+1; i++) {
	    Arrays.fill(memo[i], -1);
	}
	int res = dp(prices, len, len, memo);
	return res;
    }
    
    public static int dp(int[] prices, int len, int maxCut, int[][] memo) {
	if (memo[len][maxCut] != -1) return memo[len][maxCut];
	if (len == 0) return 0;
	if (maxCut == 0) return Integer.MIN_VALUE;

	int res = Integer.MIN_VALUE;
	for (int k = maxCut; k > 0; k--) {
	    if (k > len) continue;
	    res = Math.max(res, dp(prices, len-k, k-1, memo) + prices[k]);
	}
	memo[len][maxCut] = res;
	return res;
    }
}

