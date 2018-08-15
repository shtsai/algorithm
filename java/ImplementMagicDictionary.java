/*
	Implement a magic directory with buildDict, and search methods.

	For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

	For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

	Example 1:
	Input: buildDict(["hello", "leetcode"]), Output: Null
	Input: search("hello"), Output: False
	Input: search("hhllo"), Output: True
	Input: search("hell"), Output: False
	Input: search("leetcoded"), Output: False
	Note:
	You may assume that all the inputs are consist of lowercase letters a-z.
	For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
	Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

// Solution 2: Trie
// Pre store all valid variations in a trie
// Faster search, more space.
// 
// Time: - m:len(dict), n:len(word)
//       Build - O(m * n * 26) = Om(m * n)
//       Search - O(n)
//       
// Space: O(n^26)
// 08/15/2018
class MagicDictionary {
    class Trie {
        boolean isWord;
        Trie[] children;
        public Trie() {
            children = new Trie[26];
        }
    }
    
    /** Initialize your data structure here. */
    Trie root;
    public MagicDictionary() {
        root = new Trie();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char original = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original) {
                        continue;
                    }
                    chars[i] = c;
                    addWord(chars);
                }
                chars[i] = original;
            }
        }
    }
    
    private void addWord(char[] chars) {
        Trie node = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    private boolean exist(char[] chars) {
        Trie node = root;
        for (char c : chars) {
            if (node.children[c - 'a'] != null) {
                node = node.children[c - 'a'];
            } else {
                return false;
            }
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return exist(word.toCharArray());
    }
}


// Solution 1: HashSet
// Use a hashset to store dictionary
// Compare every word in hashset with input word
//
// Time: O(m * n) - m:len(set), n:len(word)
// Space: O(m * n)
// 08/15/2018

class MagicDictionary {

    HashSet<String> set;
    /** Initialize your data structure here. */
    public MagicDictionary() {
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        set = new HashSet<>(Arrays.asList(dict));
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (String s : set) {
            if (s.length() != word.length()) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != word.charAt(i)) {
                    diff++;
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}

