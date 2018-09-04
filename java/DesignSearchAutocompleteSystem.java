/*
    Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

    The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
    The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
    If less than 3 hot sentences exist, then just return as many as you can.
    When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
    Your job is to implement the following functions:

    The constructor function:

    AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

    Now, the user wants to input a new sentence. The following function will provide the next character the user types:

    List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

    Example:
    Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
    The system have already tracked down the following sentences and their corresponding times: 
    "i love you" : 5 times 
    "island" : 3 times 
    "ironman" : 2 times 
    "i love leetcode" : 2 times 
    Now, the user begins another search: 

    Operation: input('i') 
    Output: ["i love you", "island","i love leetcode"] 
    Explanation: 
    There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

    Operation: input(' ') 
    Output: ["i love you","i love leetcode"] 
    Explanation: 
    There are only two sentences that have prefix "i ". 

    Operation: input('a') 
    Output: [] 
    Explanation: 
    There are no sentences that have prefix "i a". 

    Operation: input('#') 
    Output: [] 
    Explanation: 
    The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search. 

    Note:
    The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
    The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
    Please use double-quote instead of single-quote when you write test cases even for a character input.
    Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

// Solution 2: Trie + HashMap
// For each trie node, store all its children sentences with their count.
// Allow for faster look up.
// Time: initialize - O(kn) - k:len(sentences), n:len(sentence)
//       input - O(k + mlogm) - sort
// Space: O(kkn) - for each sentence, store all k chars for k times (along the path from root to leaf)
// 09/03/2018
class AutocompleteSystem {
    class Pair implements Comparable<Pair> {
        String sentence;
        int count;
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
        public int compareTo(Pair other) {
            if (this.count != other.count) {
                return other.count - this.count;
            } else {
                return this.sentence.compareTo(other.sentence);
            }
        }
    }
    class Trie {
        String sentence;
        HashMap<Character, Trie> children;
        int count;
        HashMap<String, Integer> suggestions;
        public Trie() {
            children = new HashMap<>();
            suggestions = new HashMap<>();
        }
    }
    
    Trie root;
    Trie curNode;
    String curWord;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = buildTrie(sentences, times);
        curNode = root;
        curWord = "";
    }
    
    private Trie buildTrie(String[] sentences, int[] times) {
        Trie node = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            addWord(node, sentences[i], times[i]);
        }
        return node;
    }
    
    private void addWord(Trie node, String sentence, int time) {
        for (char c : sentence.toCharArray()) {
            if (node.children.get(c) == null) {
                node.children.put(c, new Trie());
            }
            node = node.children.get(c);
            node.suggestions.put(sentence, node.suggestions.getOrDefault(sentence, 0) + time);
        }
        node.sentence = sentence;
        node.count += time;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            addWord(root, curWord, 1);
            curWord = "";
            curNode = root;
            return new ArrayList<>();
        } else {
            curWord += c;
            return findSentences(c);
        }
    }
    
    private List<String> findSentences(char c) {
        if (curNode.children.get(c) == null) {
            curNode.children.put(c, new Trie());
        }
        curNode = curNode.children.get(c);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (String k : curNode.suggestions.keySet()) {
            pq.offer(new Pair(k, curNode.suggestions.get(k)));
        }
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().sentence);
        }
        return res;
    }
}

// Solution 1: Plain trie
// Use a trie to store search history.
// When adding a new input char, we update current node in the trie,
// and find all words with that prefix.
// Then we sort and return top three.
// Time: initialize - O(kn) - k:len(sentences), n:len(sentence)
//       input - O(27 ^ n + mlogm) - find all possible sentences + sort
// Space: O(27 ^ n)
// 09/03/2018

class AutocompleteSystem {
    class Trie implements Comparable<Trie> {
        String sentence;
        Trie[] children;
        int count;
        public Trie() {
            children = new Trie[27];
        }
        public int compareTo(Trie other) {
            if (this.count != other.count) {
                return other.count - this.count;
            } else {
                return this.sentence.compareTo(other.sentence);
            }
        }
    }
    
    Trie root;
    Trie curNode;
    String curWord;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = buildTrie(sentences, times);
        curNode = root;
        curWord = "";
    }
    
    private Trie buildTrie(String[] sentences, int[] times) {
        Trie node = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            addWord(node, sentences[i], times[i]);
        }
        return node;
    }
    
    private void addWord(Trie node, String sentence, int time) {
        for (char c : sentence.toCharArray()) {
            int index = c == ' ' ? 26 : c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.sentence = sentence;
        node.count += time;
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            addWord(root, curWord, 1);
            curWord = "";
            curNode = root;
            return new ArrayList<>();
        } else {
            curWord += c;
            return findSentences(c);
        }
    }
    
    private List<String> findSentences(char c) {
        PriorityQueue<Trie> pq = new PriorityQueue<>();
        int index = c == ' ' ? 26 : c - 'a';
        if (curNode.children[index] == null) {
            curNode.children[index] = new Trie();
        }
        curNode = curNode.children[index];
        finder(curNode, pq);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().sentence);
        }
        return res;
    }
    
    private void finder(Trie node, PriorityQueue<Trie> res) {
        if (node == null) {
            return;
        }
        if (node.count > 0) {
            res.offer(node);
        }
        for (int i = 0; i < node.children.length; i++) {
            finder(node.children[i], res);
        }
    }
}

