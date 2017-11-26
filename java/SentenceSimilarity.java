/*
	Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

	For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

	Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

	Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

	Note:

	The length of words1 and words2 will not exceed 1000.
	The length of pairs will not exceed 2000.
	The length of each pairs[i] will be 2.
	The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */

// Solution 1:
// Use HashMap to store pairs, so that we can have fast look up
// Then compare two words word by word
// Time: O(m + n) - m is the number of pairs, n is the length of sentence.
// Space: O(m)
// 11/25/2017

class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new HashSet<>());
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new HashSet<>());
            }
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }
        
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (map.containsKey(words1[i])) {
                if (map.get(words1[i]).contains(words2[i])) continue;
            }
            return false;
        }
        return true;
    }
}