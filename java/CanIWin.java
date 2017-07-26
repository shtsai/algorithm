/*
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 * 
 * What if we change the game so that players cannot re-use integers?
 * 
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 * 
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.
 * 
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 * 
 * Example
 * 
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 
 * Output:
 * false
 * 
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */

// Dynamic programming

// @choosable is an integer whose binary representation contains info about choosable numbers
// in its binary representation, 1 stands for choosable, 0 stands for unchoosable
// for example: 21 -> 10101   this num shows that 1, 3, 5 are choosable
// we use this integer as the key to the map

public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1+maxChoosableInteger) * maxChoosableInteger/2 < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        
        int choosable = (1 << (maxChoosableInteger+1)) - 1; 
        HashMap<Integer, Boolean> map = new HashMap();      // map contains answers to smaller subproblems
        return helper(map, choosable, maxChoosableInteger, desiredTotal);
    }
    
    private boolean helper(HashMap<Integer, Boolean> map, int choosable, int maxChoosable, int total) {
        if (total <= 0) return false;
        if (map.containsKey(choosable)) return map.get(choosable);
        
        for (int i = 1; i <= maxChoosable; i++) {
            if ((choosable & (1 << (i-1))) > 0) {    // true if the number is choosable
                int temp2 = choosable ^ (1 << (i-1));   // mark it as unchoosable
                if (!helper(map, temp2, maxChoosable, total-i)) {   // check if the opponent can win from the given configuration
                    map.put(choosable, true);
                    return true;
                }
            }
        }
        map.put(choosable, false);
        return false;
    }
}