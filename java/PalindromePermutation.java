/*
    Given a string, determine if a permutation of the string could form a palindrome.

    For example,
    "code" -> False, "aab" -> True, "carerac" -> True.
 */

// Solution 2:
// Use a set to keep track of chars that appears odd times
// Time: O(n)
// Space: O(n)
// 09/26/2017
class Solution {
    public boolean canPermutePalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(i));
            } else {
                set.add(s.charAt(i));
            }
        }
        return set.size() == 0 || set.size() == 1;
    }
}

// Solution 1:
// A palindrome can contains at most one single char.
// Record the frequency of all chars, and see if there are more than two
// single chars.
// Time: O(n)
// Space: O(1)
// 09/26/2017
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        boolean single = false;
        for (int i = 0; i < 256; i++) {
            if (freq[i] % 2 == 1) { // odd appearance
                if (single) {
                    return false;
                } else {
                    single = true;
                }
            }
        }
        return true;
    }
}