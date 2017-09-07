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
// Time: O(m) - m is the length of the string
// Space: O(nw) - n is the number of words, and w is the length of each word
//
// reference: https://discuss.leetcode.com/topic/35676/accepted-java-solution-12ms-with-explanation

        int distinctWord = 0;
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length(), wordNum = words.length;
        HashMap<String, int[]> map = new HashMap<>();  // array[0] stores the number of this words we need
                                                           // array[1] stores the number of this words we have in the substring
        int distinctWord = 0;
        for (String word : words) {
            if (!map.containsKey(word)) {       // build hashmap
                map.put(word, new int[2]);
                distinctWord++;
            }
            map.get(word)[0]++;
        }
        
        for (int i = 0; i < wordLen; i++) {
            int need = distinctWord, start = i, end = i;
            while (end <= s.length()-wordLen) {
                while (end <= s.length()-wordLen && need > 0) {
                    String word = s.substring(end, end + wordLen);  // get next word
                    if (map.containsKey(word)) {     // need this word
                        map.get(word)[1]++;      // add the counter of this word
                        if (map.get(word)[1] == map.get(word)[0]) { // find exact the number we need
                            need--;
                        }
                    }
                    end += wordLen;
                }

                // find all words we need
                while (start < end && need == 0) {
                    String word = s.substring(start, start + wordLen);
                    if (map.containsKey(word)) {
                        map.get(word)[1]--;     // decrement the counter for this word
                        if (map.get(word)[1] == map.get(word)[0]-1) {   // make sure the counter is one less than what we need
                            need++;                                     // need to do this check b/c counter can be more than what we need
                        }
                        if (end - start == wordLen * wordNum) {
                            res.add(start);
                        }
                    }
                    start += wordLen;
                }
            }
            for (String key : map.keySet()) {   // reset counters in the map
                map.get(key)[1] = 0;
            }
        }
        return res;
    }
}

// Solution 1:
// Use hashmap and two pointers
// Hashmap enables quick check if the word is valid, and it allows duplicates.
// Two pointers: 
//      - one points at the starting point of this substring
//      - the other is used to traverse s
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
        int wordLen = words[0].length(), wordNum = words.length;
        for (int start = 0; start <= s.length()-wordLen*wordNum; start++) { // try different start point
            HashMap<String, Integer> copy = new HashMap<>(map);
            for (int i = 0; i < wordNum; i++) {     // need to find all words
                String nextWord = s.substring(start+i*wordLen, start+(i+1)*wordLen);
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