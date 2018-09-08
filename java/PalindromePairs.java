/*
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 * Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */

// Solution 3:
// reference: https://discuss.leetcode.com/topic/39585/o-n-k-2-java-solution-with-trie-structure-n-total-number-of-words-k-average-length-of-each-word/2 
// Time: O(nll)
// Space: O(26 ^ l) - more space efficient as l increase
// 09/08/2018
class Solution {
    class Trie {
        Trie[] children;
        boolean isLeaf;
        int index;
        List<Integer> list;
        public Trie() {
            children = new Trie[26];
            isLeaf = false;
            index = -1;
            list = new ArrayList<>();
            
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Trie root = new Trie();
        for (int i = 0; i < words.length; i++) {
            add(root, words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
            search(root, words[i], i, res);
        }
        
        return res;
    }
    
    private void add(Trie node, String word, int i) {
        for (int j = word.length() - 1; j >= 0; j--) {
            int c = word.charAt(j) - 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            if (isPalindrome(word, 0, j)) {
                node.list.add(i);
            }
            node = node.children[c];
        }
        node.isLeaf = true;
        node.index = i;
        node.list.add(i);
    }
    
    private void search(Trie node, String word, int i, List<List<Integer>> res) {
        for (int j = 0; j < word.length(); j++) {
            int c = word.charAt(j) - 'a';
            if (node.isLeaf && node.index != i && isPalindrome(word, j, word.length() - 1)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(node.index);
                res.add(list);
            }
            if (node.children[c] == null) {
                return;
            }
            node = node.children[c];
        }
        for (Integer l : node.list) {
            if (l == i) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(i);
            list.add(l);
            res.add(list);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

// Solution 2: HashMap
// Time: O(nll) - n-len(words), l-len(word)
// Space: O(nl) - HashMap
// 09/07/2018
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;
        
        HashMap<String, Integer> map = new HashMap<>(); // stores all the (word, index) pairs
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {  // iterate through all the words
            for (int j = 0; j <= words[i].length(); j++) {  // try all the partitions, NOTE: should use "<="
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j, words[i].length());
                
                if (isPalindrome(str1)) {  // if str1 is palindrome, see if can find a reverse version of str2
                    String reverse = new StringBuilder(str2).reverse().toString();  // reverse str2
                    if (map.containsKey(reverse) && map.get(reverse) != i) {    // check if it exists and is not i itself
                        result.add(Arrays.asList(map.get(reverse),i));  
                    }
                }
                
                if (isPalindrome(str2)) { // same idea as above
                    String reverse = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(reverse) && map.get(reverse) != i  && str2.length()!=0) {
                        // str2 cannot be empty because this case have already been considered in str1
                        // when j = words[i].length(), avoid duplicates
                        result.add(Arrays.asList(i,map.get(reverse)));
                    }
                }
            }
        }
        return result;
    }
    
    public boolean isPalindrome(String word) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

// Solution 1: Try all pairs
// Time: O(k * n ^ 2) - Time limit exceeded
// Space: O(n)
// 09/07/2018
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String nw1 = words[i] + words[j];
                if (isPalindrome(nw1)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
                String nw2 = words[j] + words[i];
                if (isPalindrome(nw2)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    list.add(i);
                    res.add(list);
                }
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}