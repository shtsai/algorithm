/*
    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Given word1 = “coding”, word2 = “practice”, return 3.
    Given word1 = "makes", word2 = "coding", return 1.

    Note:
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

// Solution 1:
// Use two pointers two store the indices of the most recent occurrence
// of word1 and word2.
// When a new occurrence detected, update the min value so far.
// Time: O(n) - one pass
// Space: O(1)
// 10/07/2017

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            }
            if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                res = Math.min(res, Math.abs(index1-index2));
            }
        }
        return res;
    }
}