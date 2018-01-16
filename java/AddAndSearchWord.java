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

// Solution 1: Trie
// Implement trie to add and search word.
// Solution: add() - O(n) n is the length of the word
//           search() - O(26^n) worst case, all "."
// 01/16/2018
class WordDictionary {
    class Node {
        Node[] next;
        boolean isWord;
        
        public Node() {
            next = new Node[26];
        }
        public void setWord() {
            isWord = true;
        }
        public boolean isWord() {
            return this.isWord;
        }
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node p = root;
        for (char c : word.toCharArray()) {
            if (p.next[c - 'a'] == null) {
                p.next[c - 'a'] = new Node();
            }
            p = p.next[c - 'a'];
        }
        p.setWord();
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelper(word, root, 0);
    }
    
    private boolean searchHelper(String word, Node node, int index) {
        if (node == null || index < 0 || index > word.length()) {
            return false;
        } else if (index == word.length()) {
            return node.isWord();
        } else {
            char c = word.charAt(index);
            if (c != '.') {
                if (node.next[c - 'a'] == null) {
                    return false;
                } else {
                    return searchHelper(word, node.next[c - 'a'], index + 1);
                }
            } else {
                for (int i = 0; i < 26; i++) {
                    if (searchHelper(word, node.next[i], index + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }
}
