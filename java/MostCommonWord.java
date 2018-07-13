/*
    Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

    Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

    Example:
    Input: 
    paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    banned = ["hit"]
    Output: "ball"
    Explanation: 
    "hit" occurs 3 times, but it is a banned word.
    "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
    Note that words in the paragraph are not case sensitive,
    that punctuation is ignored (even if adjacent to words, such as "ball,"), 
    and that "hit" isn't the answer even though it occurs more because it is banned.
     

    Note:

    1 <= paragraph.length <= 1000.
    1 <= banned.length <= 100.
    1 <= banned[i].length <= 10.
    The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
    paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
    Different words in paragraph are always separated by a space.
    There are no hyphens or hyphenated words.
    Words only consist of letters, never apostrophes or other punctuation symbols.
 */

// Solution 2: StringBuilder
// Store chars into a StringBuilder. 
// Generate a new word when meet a punctuation.
// 
// Time: O(n + b) - n:#chars of paragraph, b:#chars of banned
// Space: O(n + b) - Hashmap + Hashset
// 07/13/2018
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String newP = paragraph + ".";      // append a punctution
        HashMap<String, Integer> freq = new HashMap<>();
        HashSet<String> bannedSet = new HashSet<String>(Arrays.asList(banned));
        StringBuilder sb = new StringBuilder();
        String mostCommonWord = "";
        int mostCount = 0;
        
        for (char c : newP.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (sb.length() > 0) {   // has a word
                    String word = sb.toString();
                    if (!bannedSet.contains(word)) {
                        freq.put(word, freq.getOrDefault(word, 0) + 1);
                        if (freq.get(word) > mostCount) {
                            mostCount = freq.get(word);
                            mostCommonWord = word;
                        }
                    }
                    sb = new StringBuilder();
                }
            }
        }
        return mostCommonWord;
    }
}

// Solution 1: Two pointers
// Use start and end to find words.
// Store punctuations and banned words in a hashset for quick retrieval.
// 
// Time: O(n + b) - n:#chars of paragraph, b:#chars of banned
// Space: O(n + b) - Hashmap + Hashset
// 07/13/2018

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String lowerP = paragraph.toLowerCase();
        int len = paragraph.length();
        HashMap<String, Integer> freq = new HashMap<>();
        HashSet<String> bannedSet = new HashSet<String>(Arrays.asList(banned));
        Character[] puncs = new Character[] {'!', '?', '\'', ',', ';', '.', ' '};
        HashSet<Character> punctuations = new HashSet<Character>(Arrays.asList(puncs));
        String mostCommonWord = "";
        int mostCount = 0;
        
        int start = 0, end = 0;

        while (start < len && end < len) {
            while (start < len && punctuations.contains(lowerP.charAt(start))) {
                start++;
            }
            end = start;
            while (end < len && !punctuations.contains(lowerP.charAt(end))) {
                end++;
            }
            
            String s = lowerP.substring(start, end);
            if (!bannedSet.contains(s)) {
                freq.put(s, 1 + freq.getOrDefault(s, 0));
                if (freq.get(s) > mostCount) {
                    mostCount = freq.get(s);
                    mostCommonWord = s;
                }
            }
            start = end + 1;
        }
        return mostCommonWord;
    }
}