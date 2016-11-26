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

public class Solution {

    // (1) Trie solution: 
    // https://discuss.leetcode.com/topic/39585/o-n-k-2-java-solution-with-trie-structure-n-total-number-of-words-k-average-length-of-each-word/2 

    // (2) HashMap solution
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length < 2) return result;
        
        HashMap<String, Integer> map = new HashMap<>(); // stores all the (word, index) pairs
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {  // iterate through all the words
            for (int j = 0; j <= words[i].length(); j++) {  // try all the partitions, NOTE: should use "<="
                String str1 = words[i].substring(0,j);
                String str2 = words[i].substring(j,words[i].length());
                
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
    
    // helper function for validate palindrome
    public boolean isPalindrome(String word) {
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    /*
    // (3) brute force solution, try all combinations and validate each one
    // time limit exceeded
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;   // a word cannot pair with itself
                StringBuilder sb = new StringBuilder(words[i]+words[j]);
                if (isPalindrome(sb.toString())){
                    List<Integer> list = new ArrayList<>(Arrays.asList(i,j));
                    result.add(list);
                }
            }
        }
        return result;
    }
    
    // helper function for validate palindrome
    public boolean isPalindrome(String word) {
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    */
}