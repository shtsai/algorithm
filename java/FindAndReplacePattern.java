/*
    You have a list of words and a pattern, and you want to know which words in words matches the pattern.
    A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
    (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
    Return a list of the words in words that match the given pattern.
    You may return the answer in any order.

    Example 1:
    Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
    Output: ["mee","aqq"]
    Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
    "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
    since a and b map to the same letter.

    Note:
    1 <= words.length <= 50
    1 <= pattern.length = words[i].length <= 20
 */


// Solution 1: HashMap
// Use a HashMap to keep track of the mappings.
// And use a HashSet to make sure there are no duplicate mappings
// Time: O(mn) - m:len(words), n:len(word)
// Space: O(n)
// 08/19/2018

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (isMatch(word, pattern)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isMatch(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> used = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (map.containsKey(p)) {
                if (map.get(p) != w) {
                    return false;
                }
            } else if (used.contains(w)) {
                return false;
            } else {
                map.put(p, w);
                used.add(w);
            }
        }
        return true;
    }
}
