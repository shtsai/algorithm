/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

// Solution 3:
// Two-end BFS
// same approach as solution 2, the only difference is that we do BFS from both end
// reference: https://discuss.leetcode.com/topic/10372/share-my-two-end-bfs-in-c-80ms
// explanation: https://discuss.leetcode.com/topic/100112/two-end-bfs-runs-33ms-with-explanation/2

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        HashSet<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        HashSet<String> endSet = new HashSet<>();
        endSet.add(endWord);
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);
        int count = 1;
        
        while (!startSet.isEmpty() && !endSet.isEmpty()) {    // haven't reached it
            if (startSet.size() > endSet.size()) {  // start from the smaller set
                HashSet<String> temp = startSet;
                startSet = endSet;
                endSet = temp;
            }
            
            HashSet<String> newReached = new HashSet<String>();
            for (String s : startSet) {
                for (int i = 0; i < s.length(); i++) {
                    char[] chars = s.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String t = new String(chars);
                        if (endSet.contains(t)) return count+1; 
                        if (dict.contains(t) && !visited.contains(t)) {
                            newReached.add(t);
                            dict.remove(t);
                        }
                    }
                }
            }
            count++;    // reached a new level
            startSet = newReached;
        }
        return 0;
    }   
}

// Solution 2: a modified version of solution 1
// the use of helper function in solution 1 actually makes the problem slower:
// (1) Assume there are n words in wordList, we reached m words, the length of each word is l
// there are around m * n * l operations
// (2) In comparasion, if we check all 26 chars (a-z), we have m * 26 * l operations, (in hashset, .contains() is O(1))
// Therefore, second solution is much faster when n is large
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> reached = new HashSet<>();
        HashSet<String> dict = new HashSet<>(wordList);
        reached.add(beginWord);
        int count = 1;
        
        while (!reached.contains(endWord)) {
            HashSet<String> newReached = new HashSet<String>();
            for (String s : reached) {
                for (int i = 0; i < s.length(); i++) {
                    char[] chars = s.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String t = new String(chars);
                        if (dict.contains(t)) {
                            newReached.add(t);
                            dict.remove(t);
                        }
                    }
                }
            }
            if (newReached.size() == 0) return 0;   // cannot reach new words
            count++;    // reached a new level
            if (newReached.contains(endWord)) return count;
            reached = newReached;
        }
        return count;
    }   
}

// Solution 1:
// One-end BFS, search level by level, until reach endWord or cannot reach new words
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> reached = new HashSet<>();
        reached.add(beginWord);
        int count = 1;
        
        while (!reached.contains(endWord)) {
            HashSet<String> newReached = new HashSet<String>();
            for (String s : reached) {
                for (int i = 0; i < wordList.size();) {
                    String t = wordList.get(i);
                    if (isValid(s, t)) {
                        newReached.add(t);
                        wordList.remove(i);
                        continue;
                    } else { i++; }
                }
            }
            if (newReached.size() == 0) return 0;   // cannot reach new words
            count++;    // reached a new level
            if (newReached.contains(endWord)) return count;
            reached = newReached;
        }   
        return count;
    }
    
    // a helper function that checks if two strings represents a valid transformation
    private boolean isValid(String a, String b) {
        if (a.length() != b.length()) return false;
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
