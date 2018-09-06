/*
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * 
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * 
 * You should return the indices: [0,9].
 * (order does not matter).
 */

// Solution 2:
// Use ideas from Minimum Windows Substring
// Use a Hashmap to store words, their expected counts and actually counts
// array[0] stores the number of this words we need
// array[1] stores the number of this words we have in the substring
//
// Then use variable need as an indicator of whether we need more words
// Start and end work as two pointers to represent the window
// Time: O(mw) - m:len(s), w:len(word)
// Space: O(nw) - n:len(words), w:len(word)
// 09/06/2018

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) {
            return res;
        }
        int len = words[0].length();
        int wordLen = words.length;
        HashMap<String, Integer> freq = new HashMap<>();
        HashMap<String, Integer> seen = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
            seen.put(w, 0);
        }
        for (int i = 0; i < len; i++) {     // only need to try `len` start position
            int need = freq.keySet().size();
            int left = i, right = i;
            while (right + len <= s.length()) {
                while (right + len <= s.length() && need != 0) {
                    String sub = s.substring(right, right + len);
                    if (seen.containsKey(sub)) {
                        seen.put(sub, seen.get(sub) + 1);
                        if (freq.get(sub) == seen.get(sub)) {
                            need--;
                        }           
                    }
                    right += len;
                }
                
                while (left < right && need == 0) {
                    if (right - left == wordLen * len) {
                        res.add(left);
                    }
                    String prev = s.substring(left, left + len);
                    if (seen.containsKey(prev)) {
                        seen.put(prev, seen.get(prev) - 1);
                        if (freq.get(prev) > seen.get(prev)) {
                            need++;
                        }
                    } 
                    left += len;
                }                
            }
            for (String k : freq.keySet()) {    // reset seen for next iteration
                seen.put(k, 0);
            }
        }
        return res;
    }
}

// Solution 1:
// Use hashmap and two pointers
// Hashmap enables quick check if the word is valid, and it allows duplicates.
// Two pointers: 
//      - `start` points at the starting point of this substring
//      - `i` is used to traverse s
// Time: O(mn), m = length of s, n is length of words
// Space: O(mnw), we make m copies of map, which contains n words of length w
//                may be optimized to use less space
// Time limit exceeded
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0 || s.length() == 0) return res;
        
        // use HashMap for quick access, and store counts because may contain duplicates
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {     // build a map for words and their counts
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word)+1);
            }
        }
        int wordLen = words[0].length();
        int wordNum = words.length;
        for (int start = 0; start <= s.length()- wordLen * wordNum; start++) { // try different start point
            HashMap<String, Integer> copy = new HashMap<>(map);
            for (int i = 0; i < wordNum; i++) {     // need to find all words
                String nextWord = s.substring(start + i * wordLen, start + (i + 1) * wordLen);
                if (copy.containsKey(nextWord)) {   // if map contains next word
                    if (copy.get(nextWord) == 1) {   // last count, can remove it
                        copy.remove(nextWord);
                    } else {
                        copy.put(nextWord, copy.get(nextWord)-1);
                    }
                } else {    // cannot find words continously
                    break;
                }
            }
            if (copy.size() == 0) {  // find all words, valid start
                res.add(start);
            }
        }
        return res;
    }
}