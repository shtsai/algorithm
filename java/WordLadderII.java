/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * 
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */

// Solution 2:
// First do BFS, to find the distances from all nodes to the beginword
// After that we do DFS to construct all valid ladders by only allowing ladder that is one level apart
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        
        HashMap<String, Integer> distances = new HashMap<>();   // contains the shortest distance from word to beginWord
        distances.put(beginWord, 0);
        HashMap<String, List<String>> next = new HashMap<>();   // contains the neighbors of the word
        next.put(beginWord, new ArrayList<>());
        for (String word : wordList) next.put(word, new ArrayList<>());
        Queue<String> q = new LinkedList<>();       // q contains nodes whose shortest distance have been found
        q.offer(beginWord);
        
        // BFS
        while (!q.isEmpty()) {
            int count = q.size();
            while (count > 0) {    // process level by level
                String cur = q.poll();
                int cur_dis = distances.get(cur);
                // find neighbors
                for (int i = 0; i < cur.length(); i++) {
                    char[] chars = cur.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String t = new String(chars);
                        if (dict.contains(t)) {     // find a neighbor
                            next.get(cur).add(t);
                            if (!distances.containsKey(t)) {   // haven't visited yet
                                distances.put(t, cur_dis + 1);
                                q.offer(t);
                            }
                            if (t.equals(endWord)) break;   // find end word
                        }
                    }
                }
                count--;
            }
        }
        
        List<String> ladder = new ArrayList<>();
        ladder.add(beginWord);
        finder(res, ladder, endWord, dict, distances, next);
        return res;
    } 
    
    private void finder(List<List<String>> res, List<String> ladder, String endWord, Set<String> dict, HashMap<String, Integer> distances, HashMap<String, List<String>> next) {
        if (ladder.size() == 0) return;
        String lastWord = ladder.get(ladder.size()-1);
        if (lastWord.equals(endWord)) {   // find a complete ladder
            res.add(new ArrayList<String>(ladder));
            return;
        }
        
        int cur_dis = distances.get(lastWord);
        for (String node : next.get(lastWord)) {
            if (distances.get(node) == cur_dis + 1) {   // only allow words that is one unit further than current word
                ladder.add(node);
                finder(res, ladder, endWord, dict, distances, next);
                ladder.remove(ladder.size()-1);
            }
        }
        
        return;
    }
}

// Solution 1:
// DFS, find all valid ladders, then only keep the shortest ones.
// Time limit exceeded
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        List<String> ladder = new ArrayList<>();
        ladder.add(beginWord);
        finder(res, ladder, endWord, dict);
        
        int minLen = Integer.MAX_VALUE;
        for (List<String> list : res) minLen = Math.min(minLen, list.size());
        
        int i = 0;
        while (i< res.size()) {
            if (res.get(i).size() > minLen) res.remove(i);
            else i++;
        }
        
        return res;
    }
    
    private void finder(List<List<String>> res, List<String> ladder, String endWord, Set<String> dict) {
        if (ladder.get(ladder.size()-1).equals(endWord)) {   // find a complete ladder
            res.add(new ArrayList<String>(ladder));
            return;
        }
        for (int i = 0; i < endWord.length(); i++) {
            char[] chars = ladder.get(ladder.size()-1).toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String t = new String(chars);
                if (dict.contains(t)) {
                    ladder.add(t);
                    dict.remove(t);
                    finder(res, ladder, endWord, dict);
                    dict.add(t);
                    ladder.remove(ladder.size()-1);
                }
            }
        }
        return;
    }
}