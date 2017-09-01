/*
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only  * letters a-z or .. A . means it can represent any one letter. * 
 * 
 * For example:
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
 /**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

class WordDictionary {
    private class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] next;
        public TrieNode(char val) {
            this.val = val;
            next = new TrieNode[26];
        }
    }
    
    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode('0');
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.next[c-'a'] == null) p.next[c-'a'] = new TrieNode(c);
            p = p.next[c-'a'];
        }
        p.isWord = true;    // this node is the end of a word
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }
    
    /** a helper function that checks whether current character is in the trie */
    private boolean searchHelper(String word, int index, TrieNode node) {
        if (index == word.length() && node.isWord) return true; // find the word
        if (index < 0 || index >= word.length() || node == null) return false;
        char c = word.charAt(index);
        if (c == '.') {     // wildcard
            for (int i = 0; i < node.next.length; i++) {
                if (node.next[i] != null && searchHelper(word, index+1, node.next[i])) return true;
            }
        } else if (node.next[c-'a'] != null) {  // find a matching character
            return searchHelper(word, index+1, node.next[c-'a']);
        }
        return false;
    }
}

