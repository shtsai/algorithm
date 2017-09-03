/*
    ** Refdash **

    Game of squares
    Two players
    Turn: Subtracting a perfect square from the current state
    State: Always a non-negative integer

    50
    41
    37
    21
    ....
 */

// Solution 2:
// iterative implementation
// Dynamic programming
// time: O(n * sqrt(n)): outer loop is O(n), inner loop is O(sqrt(n))
Class Solution {
    public int getOptimalMoveIterative(int currentState) {
        Integer[] dp = new Integer[currentState+1];
        dp[0] = -1;
        for (int i = 1; i <= currentState; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (dp[i - j*j] == -1) {
                    dp[i] = j * j;
                }
            }
            if (dp[i] == null) dp[i] = -1;
        }
        return dp[currentState];
    }
}

// Solution 1:
// recursive implementation
// Dynamic programming
// time: O(n * sqrt(n))
Class Solution {
    public int getOptimalMove(int currentState) {
        Integer[] dp = new int[currentState];
        return helper(currentState, dp);
    }

    /* a helper function to build dp array */
    public int helper(int currentState, Integer[] dp) {
        if (currentState <= 0) return -1;
        if (dp[currentState] != null) return dp[currentState];
        
        for (int i = 1; i*i <= currentState; i++) {
            if (helper(currentState-i*i, dp) == -1) {
                dp[currentState] = i*i;
                return i*i;
            }
        }
        dp[currentState] = -1;
        return -1;
    }
}

