/*
	We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
	A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
	Return a list of all uncommon words. 
	You may return the list in any order.

	Example 1:
	Input: A = "this apple is sweet", B = "this apple is sour"
	Output: ["sweet","sour"]
	
	Example 2:
	Input: A = "apple apple", B = "banana"
	Output: ["banana"]
	 
	Note:
	0 <= A.length <= 200
	0 <= B.length <= 200
	A and B both contain only spaces and lowercase letters.
 */

// Solution 2: One hashmap
// Uncommon words only appear once.
//
// Time: O(m + n)
// Space: O(m + n)
// 08/12/2018
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : A.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String word : B.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String> res = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                res.add(key);
            }
        }
        return res.toArray(new String[res.size()]);
    }
}

// Solution 1: Two hashmaps
// Use two hashmap to maintain the frequency of words.
// Then find unique words that don't appear in the other map
//
// Time: O(m + n)
// Space: O(m + n)
// 08/11/2018

class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        String[] wordsA = A.split(" ");
        String[] wordsB = B.split(" ");
        HashMap<String, Integer> mapA = new HashMap<>();
        HashMap<String, Integer> mapB = new HashMap<>();
        
        for (String word : wordsA) {
            if (mapA.containsKey(word)) {
                mapA.put(word, mapA.get(word) + 1);
            } else {
                mapA.put(word, 1);
            }
        }
        
        for (String word : wordsB) {
            if (mapB.containsKey(word)) {
                mapB.put(word, mapB.get(word) + 1);
            } else {
                mapB.put(word, 1);
            }
        }
        
        List<String> res = new ArrayList<>();
        for (String word : wordsA) {
            if (mapA.get(word) == 1 && !mapB.containsKey(word)) {
                res.add(word);
            }
        }
        for (String word : wordsB) {
            if (mapB.get(word) == 1 && !mapA.containsKey(word)) {
                res.add(word);
            }
        }
        
        String[] resArray = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }
}