/*
    Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

    Note:

    A word cannot be split into two lines.
    The order of words in the sentence must remain unchanged.
    Two consecutive words in a line must be separated by a single space.
    Total words in the sentence won't exceed 100.
    Length of each word is greater than 0 and won't exceed 10.
    1 ≤ rows, cols ≤ 20,000.
    Example 1:

    Input:
    rows = 2, cols = 8, sentence = ["hello", "world"]

    Output: 
    1

    Explanation:
    hello---
    world---

    The character '-' signifies an empty space on the screen.
    Example 2:

    Input:
    rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

    Output: 
    2

    Explanation:
    a-bcd- 
    e-a---
    bcd-e-

    The character '-' signifies an empty space on the screen.
    Example 3:

    Input:
    rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

    Output: 
    1

    Explanation:
    I-had
    apple
    pie-I
    had--

    The character '-' signifies an empty space on the screen.
 */

// INCOMPLETED

// Solution 2: Memoization
// Use a help function nextStart() to get the next index of r and c.
// Since there are only n cols, only need n calls to nextStart().
// Time: O(ns) - n = cols, s = len(sentence) => Time limit exceeded
// Space: O(n)
// 01/10/2018
class Solution {
    int MAX;
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int r = 0, c = 0, count = 0;
        MAX = rows;
        List<Integer>[] memo = new List[cols+1];
        
        while (true) {
            List<Integer> next = nextStart(sentence, cols, c, memo);
            if (next.get(0) + r >= rows) {
                if (next.get(0) + r == rows && next.get(1) == 0) {
                    count++;
                }
                break;
            } else {
                r += next.get(0);
                c = next.get(1);
                count++;
            }
        }
        
        return count;
    }
    
    public List<Integer> nextStart(String[] sentence, int cols, int current, List<Integer>[] memo) {
        if (memo[current] != null) {
            return memo[current];
        }
        List<Integer> res = new ArrayList<>();
        int r = 0, c = current, i = 0;
        while (i < sentence.length) {
            String word = sentence[i];
            if (word.length() > cols) {
                r = MAX;
                c = MAX;
                break;
            } else if (c + word.length() <= cols) {
                c += word.length() + 1;
                i++;
            } else {
                r++;
                c = 0;
            }
        }
        if (c > cols) {
            c = 0;
            r++;
        }       
        res.add(r);
        res.add(c);
        memo[current] = res;
        return res;
    }
}

// Solution 1: Brute force
// Repeatedly try to fill the sentence into the screen.
// Time: O(rc) - r = rows, c = cols => Time limit exceeded
// Space: O(1)
// 01/10/2018

class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int r = 0, c = 0, count = 0;
        while (true) {
            boolean finished = true;
            int i = 0;
            while (i < sentence.length) {
                String word = sentence[i];
                if (c + word.length() <= cols) {
                    c += word.length() + 1;
                    i++;
                } else if (r < rows - 1) {
                    r++;
                    c = 0;
                } else {
                    finished = false;
                    break; 
                }
            }
            if (finished) count++;
            else break;
        }
        return count;
    }
}