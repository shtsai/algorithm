/*
 *  Implement a trie with insert, search, and startsWith methods.
 * 
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z. 
 */

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

// reference: https://leetcode.com/articles/implement-trie-prefix-tree/

// Solution 1: Standard implementation
// Each node will have 26 edges pointing to the next node.
// Each edge represents a character.
// Each node has a boolean value that indicates whether it is the end of a word.
// Time: Insert - O(n) - n is the length of word
//       Search - O(n)
//       StartsWith - O(n)
// Space: O(m) - m is the total length of all words
// 01/15/2018

class Trie {
    class Node {
        Node[] next;
        boolean isWord;
        
        public Node() {
            next = new Node[26];
        }
    }
    
    Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node p = root;
        for (char c : word.toCharArray()) {
            if (p.next[c - 'a'] == null) {
                p.next[c - 'a'] = new Node();
            }
            p = p.next[c - 'a'];
        }
        p.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node node = searchPrefix(prefix);
        return node != null;
    }
    
    private Node searchPrefix(String prefix) {
        Node p = root;
        for (char c : prefix.toCharArray()) {
            if (p.next[c - 'a'] == null) {
                return null;
            } else {
                p = p.next[c - 'a'];
            }
        }
        return p;
    }
}
