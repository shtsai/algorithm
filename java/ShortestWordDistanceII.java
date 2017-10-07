/*
    This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

    Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Given word1 = “coding”, word2 = “practice”, return 3.
    Given word1 = "makes", word2 = "coding", return 1.

    Note:
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */

// Solution 1: HashMap
// Use a hashmap to store the indices of all occurrence of words.
// Subsequence lookups can just get the list of indices from the map,
// and compute all combinations.
// Time: O(n) insert, O(1) look up 
// Space: O(n)
// 10/07/2017

class WordDistance {
    HashMap<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<Integer>());
            }
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        
        int res = Integer.MAX_VALUE;
        for (int i : list1) {
            for (int j : list2) {
                res = Math.min(res, Math.abs(i-j));
            }
        }
        return res;
    }
}

