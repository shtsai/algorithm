/* 
    Maximum Sum At Most One Skip
    Given a array of number(positive, negative, zero),
    return the maximum sum.

    Can skip at most one number.

    e.g.
        [1*, 2*, -3, 2*, 4*]   -> 9
        [1*, 2*, -1*, -3, 4*]  -> 6

    PocketGem
    11/11/2017
 */
    /*
     * Complete the function below.
     */
    static int maximizeRatings(int[] ratings) {
        if (ratings.length == 1) return Math.max(ratings[0], 0);
        
        // each bucket i represents the max ratings possible formed from index 0 to index i
        int[] dp = new int[ratings.length];
        dp[0] = ratings[0];
        dp[1] = Math.max(dp[0], 0) + ratings[1];
        for (int i = 2; i < ratings.length; i++) {
            // choose dp[i-2] to skip dp[i-1]
            // or choose dp[i-1] 
            dp[i] = Math.max(dp[i-2], dp[i-1]) + ratings[i];
        }
        // return the max of the last two buckets b/c might not include the last movie
        return Math.max(dp[ratings.length-2], dp[ratings.length-1]);
    }
