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

// Improved solution 2:
// remove unnecessary tests
class Trie {
    class Node {
        boolean isLeaf;
        char c;
        HashMap<Integer, Node> next;
        public Node (char c) {
            this.c = c;
            this.next = new HashMap<>();    // contains pointers to 26 chars
        }
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node('0');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (!p.next.containsKey(current-'a')) {
                p.next.put(current-'a', new Node(current));
            } 
            p = p.next.get(current-'a');
        }
        // once we reach this point, p is the last node of the word
        p.isLeaf = true;  // reach last char in the word
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (!p.next.containsKey(current-'a')) return false;
            p = p.next.get(current-'a');
        }
        // at this point, p must not be null, no need to test
        return p.isLeaf;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            char current = prefix.charAt(i);
            if (!p.next.containsKey(current-'a')) return false;
            p = p.next.get(current-'a');
        }
        // at this point, p must not be null, no need to test
        return true;
    }
}


// Solution 2:
// use HashMap to store pointers to next character
class Trie {
    class Node {
        boolean isLeaf;
        char c;
        HashMap<Integer, Node> next;
        public Node (char c) {
            this.c = c;
            this.next = new HashMap<>();    // contains pointers to 26 chars
        }
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node('0');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (!p.next.containsKey(current-'a')) {
                p.next.put(current-'a', new Node(current));
            } 
            p = p.next.get(current-'a');
            if (i == word.length()-1) p.isLeaf = true;  // reach last char in the word
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            if (!p.next.containsKey(current-'a')) return false;
            p = p.next.get(current-'a');
        }
        return (p != null && p.isLeaf);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            char current = prefix.charAt(i);
            if (!p.next.containsKey(current-'a')) return false;
            p = p.next.get(current-'a');
        }
        return p != null;
    }
}


// Solution 1:
// use an array of pointers to next character
class Trie {
    class Node {
        boolean isLeaf;
        char c;
        Node[] next;
        public Node (char c) {
            this.c = c;
            this.next = new Node[26];    // contains pointers to 26 chars
        }
    }
    
    private Node root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node('0');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            Node next = p.next[current-'a'];
            if (next == null) {
                p.next[current-'a'] = new Node(current);
                next = p.next[current-'a'];
            } 
            p = next;
            if (i == word.length()-1) p.isLeaf = true;  // reach last char in the word
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node p = root;
        for (int i = 0; i < word.length(); i++) {
            char current = word.charAt(i);
            Node next = p.next[current-'a'];
            if (next == null) {
                return false;
            }
            p = next;
        }
        return (p != null && p.isLeaf);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            char current = prefix.charAt(i);
            Node next = p.next[current-'a'];
            if (next == null) {
                return false;
            }
            p = next;
        }
        return p != null;
    }
}
