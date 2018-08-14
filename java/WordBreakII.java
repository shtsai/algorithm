/*
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 *
 * UPDATE (2017/1/4):
 * The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

// https://leetcode.com/problems/word-break-ii/solution/

// Solution 3: DFS + Memoization
// Use a map to memoize the result for smaller problems.
// Build the result buttom-up.
// reference: https://discuss.leetcode.com/topic/27855/my-concise-java-solution-based-on-memorized-dfs

// version 2:
// Instead of creating substrings, use a integer to indicate start index.
// Can also be used as key to the hashMap
//
// Time: O(n^2)
// Space: O(n)
// 11/07/2017
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> map = new HashMap<>();
        return breaker(s, dict, map, 0);
    }

    private List<String> breaker(String s, Set<String> dict, HashMap<Integer, List<String>> map, int start) {
        List<String> res = new LinkedList<>();
        if (map.containsKey(start)) {  // already exist
            return map.get(start);
        }
        if (start == s.length()) {  // base case
            res.add("");
            return res;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(start, i))) {
                List<String> sublist = breaker(s, dict, map, i);
                for (int j = 0; j < sublist.size(); j++) { // insert this word to the front
                    String space = sublist.get(j).length() == 0 ? "" : " ";
                    res.add(new String (s.substring(start, i) + space + sublist.get(j)));
                }
            }
        }
        map.put(start, res);
        return res;
    }
}

// version 1
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        HashMap<String, List<String>> map = new HashMap<>();
        return wordFinder(s, dict, map);
    }

    private List<String> wordFinder(String s, Set<String> dict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) return map.get(s);

        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {  // empty string
            list.add("");
            return list;
        }

        for (int i = 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(0, i))) {
                // get the result for the remaining subproblems
                List<String> sublist = wordFinder(s.substring(i), dict, map);

                // insert current word to the front of the result of subproblems
                for (String sub : sublist) {
                    list.add(s.substring(0,i) + (sub.isEmpty()?"":" ") + sub);
                }
            }
        }
        map.put(s, list);   // store result in the map for later access
        return list;
    }
}

// Solution 2:
// Backtracking approach
// recursively build result
// Time: O(n!) - Time limit exceeded
// Space: O(n + m) - n:len(s), m:len(wordDict)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        wordFinder(s, dict, res, "");
        return res;
    }

    private void wordFinder(String s, Set<String> dict, List<String> res, String cur) {
        if (s == null || s.length() == 0) {
            res.add(new String(cur));   // find all words
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(0, i))) {
                String space = cur.length() == 0 ? "" : " ";
                wordFinder(s.substring(i), dict, res, cur+space+s.substring(0,i));
            }
        }
    }
}

// Solution 1:
// Dynamic programming, a modified version of wordBreakI
// In dp array, dp[i] contains a list of string that can be form using s[0:i+1]
// Repeat this process until reaches the end
// Time limit exceeded
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String>[] dp = new List[s.length()+1];
        dp[0] = new ArrayList<String>();
        dp[0].add("");

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dict.contains(s.substring(j, i)) && dp[j] != null) {
                    if (dp[i] == null) {
                        dp[i] = new ArrayList<String>();
                    }
                    for (String t : dp[j]) { // append current word to previous string
                        String space = t.length() == 0 ? "" : " ";
                        dp[i].add(t + space + s.substring(j,i));
                    }
                }
            }
        }
        // if (dp[s.length()]==null) return a empty list
        return dp[s.length()]==null ? new ArrayList<String>() : dp[s.length()];
    }
}
