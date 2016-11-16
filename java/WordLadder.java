/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
*/

// transform the problem into a graph
// we are trying to reach the destination from the source
// use BFS to explore all reachable nodes step by step
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        HashSet<String> reached = new HashSet<>();
        int steps = 1;
        reached.add(beginWord);         // reached nodes
        wordList.add(endWord);          // unreached notes
        
        while (!reached.contains(endWord)) { 
            HashSet<String> newReached = new HashSet<>();     // a new set to keep track of the newly reached nodes
            for (String word : reached) {
                for (int i = 0; i < word.length(); i++) {  // try all positions
                    char[] chars = word.toCharArray();
                    for (char x = 'a'; x <= 'z'; x++) {    // try all possible characters
                        chars[i] = x;
                        String newWord = new String(chars);
                        if (wordList.contains(newWord)) {  // reached a new node, add to the list
                            newReached.add(newWord);
                            wordList.remove(newWord);
                        }
                    }
                }
            }
            steps++;        // use one step in each iteration of the loop
            if (newReached.size() == 0) return 0;  // no way to reach new nodes, so no way to reach destination
            reached = newReached;   // update the reached set, now expand from the newly reached nodes
        }
        
        return steps;
    }
}