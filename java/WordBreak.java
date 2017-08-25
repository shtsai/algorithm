/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

// Solution 2:
// dynamic programming
// use array to memoize results of smaller subproblems
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];	// dp[i] = true if string[0:i] is valid
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // check dp array before check dict b/c access array is faster
                if (dp[j] && dict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    continue;   // substring(0, i) is valid
                }
            }
        }
        return dp[s.length()];
    }
}

// Solution 1:
// solve recursively
// time limit exceeded O(n2) time
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.equals("") || s.length() == 0) return true;
        HashSet<String> dict = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (dict.contains(sub) && wordBreak(s.substring(i, s.length()),wordDict)) return true;
        }
        return false;
    }
}