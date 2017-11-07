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

// Solution 2: Dynamic programming
// Use array to memoize results of smaller subproblems.
// Each cell i represent whether we can break substring (0, i].
// So we need to look back, and find substring (0, j] which is breakable.
// We check if (j, i] is in the dictionary. If so, (0, i] is also breakable.
// Otherwise, continue searching
// 
// Time: O(n^2)
// Space: O(n)
// 11/06/2017
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;   // empty string
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

// Solution 2: BFS
// Given a start index, try to go as far as possible using the words
// in the dictionary. Returns true when we reach the end of the s.
// Time: O(n^2)
// Space: O(n)
// 11/06/2017
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String n : wordDict) {
            dict.add(n);
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] visited = new boolean[s.length()];
        
        while (!q.isEmpty()) {
            int start = q.poll();
            if (start == s.length()) return true;
            if (!visited[start]) {
                for (int end = start+1; end <= s.length(); end++) {
                    if (dict.contains(s.substring(start, end))) {
                        q.offer(end);       // continue searching
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }
}

// Solution 1: DFS (brute force)
// Recursion and backtracking
// Time: O(n^n) - time limit exceeded 
// Space: O(n) - worst case call stack 
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