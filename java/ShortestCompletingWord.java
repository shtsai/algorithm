/*
	Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate

	Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.

	It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

	The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.

	Example 1:
	Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
	Output: "step"
	Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
	Note that the answer is not "step", because the letter "s" must occur in the word twice.
	Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
	Example 2:
	Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
	Output: "pest"
	Explanation: There are 3 smallest length words that contains the letters "s".
	We return the one that occurred first.
	Note:
	licensePlate will be a string with length in range [1, 7].
	licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
	words will have a length in the range [10, 1000].
	Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 */

// Solution 1:
// First extract all alphabetic characters and turn them to lowercase.
// Next scan through the words to find the shortest one that contains
// all characters. Need to be careful with duplicates.
// Time: O(mnw) m: # chars in licensePlate, n: # words, w: # chars in a word
// Space: O(1)
// 12/16/2017

class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        List<Character> chars = new ArrayList();
        for (char c : licensePlate.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                chars.add(c);
            }
        }
        int len = Integer.MAX_VALUE;
        String res = "";
        for (String word : words) {
            boolean findAll = true;
            HashSet<Integer> used = new HashSet<>();
            for (int i = 0; i < chars.size(); i++) {
                boolean find = false;
                for (int j = 0; j < word.length(); j++) {
                    if (chars.get(i) == word.charAt(j) && !used.contains(j)) {
                        find = true;
                        used.add(j);
                        break;
                    }     
                }
                if (!find) {
                    findAll = false;
                    break;
                }
            }
            if (findAll && word.length() < len) {
                len = word.length();
                res = word;
            }
        }
        return res;
    }
}