/*
	Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

	Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

	Examples:
	Input:
	letters = ["c", "f", "j"]
	target = "a"
	Output: "c"

	Input:
	letters = ["c", "f", "j"]
	target = "c"
	Output: "f"

	Input:
	letters = ["c", "f", "j"]
	target = "d"
	Output: "f"

	Input:
	letters = ["c", "f", "j"]
	target = "g"
	Output: "j"

	Input:
	letters = ["c", "f", "j"]
	target = "j"
	Output: "c"

	Input:
	letters = ["c", "f", "j"]
	target = "k"
	Output: "c"
	Note:
	letters has a length in range [2, 10000].
	letters consists of lowercase letters, and contains at least 2 unique letters.
	target is a lowercase letter.
 */

// Solution 1: 
// Compute the distance between letter and target, and keep
// track of the min distance.
// Note that if we have a positive min, then we cannot accept a negative min.
// In order words, positive min has high priority. Only choose negative min
// when no positive found.
// 12/09/2017

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char res = '0';
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < letters.length; i++) {
            int d = letters[i] - target;
            if ((diff < 0 && d > 0) || (d < diff && d != 0)) {
                res = letters[i];
                diff = d;
            }
        }
        return res;
    }
}