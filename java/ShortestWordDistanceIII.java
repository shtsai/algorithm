/*
    This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

    word1 and word2 may be the same and they represent two individual words in the list.

    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Given word1 = “makes”, word2 = “coding”, return 1.
    Given word1 = "makes", word2 = "makes", return 3.
    
    Note:
    You may assume word1 and word2 are both in the list.
 */

// Solution 1: two pointers
// Similar to ShortestWordDistanceI.java
// Need special handing for cases where word1 == word2.
// In such case, copy the value of index2 to index1,
// and update index2 with the new index value.
// This way, we are guarenteed to get two newest values,
// which could possibly produce new min.
// Time: O(n)
// Space: O(1)
// 10/07/2017
    
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int res = words.length;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2)) {
                    index1 = index2;
                    index2 = i;
                } else {
                    index1 = i;
                }
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                res = Math.min(res, Math.abs(index1-index2));
            }
        }
        return res;
    }
}