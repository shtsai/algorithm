/*
    Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

    For example,
    Given words = ["oath","pea","eat","rain"] and board =

    [
      ['o','a','a','n'],
      ['e','t','a','e'],
      ['i','h','k','r'],
      ['i','f','l','v']
    ]
    Return ["eat","oath"].
    Note:
    You may assume that all inputs are consist of lowercase letters a-z.

    click to show hint.

    You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

    If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */


// Solution 2: Trie
// First add all words into a trie.
// Then do dfs on the board to find words.
// Can break early if no word starts with the current prefix.
// 01/16/2018
class Solution {
    class Node {
        Node[] next;
        boolean isWord;
        public Node () {
            next = new Node[26];
        }
    }
    
    private Node root;
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        root = initializeTrie(words);
        HashSet<String> set = new HashSet<>();
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(set, board, used, i, j, "");
            }
        }
        List<String> res = new ArrayList<>(set);
        return res;
    }
    
    private void search(HashSet<String> res, char[][] board, boolean[][] used, int i, int j, String current) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || used[i][j]) {
            return;
        } 
        used[i][j] = true;
        String newString = current + board[i][j];
        if (startsWith(newString)) {
            if (searchWord(newString)) {
                res.add(new String(newString));
            }
            for (int[] d : dirs) {
                search(res, board, used, i + d[0], j + d[1], newString);
            }
        } 
        used[i][j] = false;
    }
    
    private Node initializeTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            addWord(root, word);
        }
        return root;
    }
    
    private void addWord(Node root, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (root.next[word.charAt(i) - 'a'] == null) {
                root.next[word.charAt(i) - 'a'] = new Node();
            }
            root = root.next[word.charAt(i) - 'a'];
        }
        root.isWord = true;
    }
    
    private boolean searchWord(String word) {
        Node node = searchPrefix(word);
        return node.isWord;
    }
    
    private boolean startsWith(String prefix) {
        Node node = searchPrefix(prefix);
        return node != null;
    }
    
    private Node searchPrefix(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (p.next[prefix.charAt(i) - 'a'] == null) {
                return null;
            } else {
                p = p.next[prefix.charAt(i) - 'a'];
            }
        }
        return p;
    }
}

// Solution 1: Search individual words
// Reuse Word Search I
//
// Time: O(n * 4^w)
// Space: O(mn)
// 08/12/2018

class Solution {
    final int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : new HashSet<String>(Arrays.asList(words))) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }    
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if (search(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int index, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c] || board[r][c] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[r][c] = true;
        for (int[] dir : dirs) {
            if (search(board, word, index + 1, r + dir[0], c + dir[1], visited)) {
                return true;
            }
        }
        visited[r][c] = false;   // reset visited so that we can use this char later in another path
        return false;
    }
}