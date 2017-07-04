/*
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
 * 
 * Example:
 * 
 * Given n = 3. 
 * 
 * At first, the three bulbs are [off, off, off].
 * After first round, the three bulbs are [on, on, on].
 * After second round, the three bulbs are [on, off, on].
 * After third round, the three bulbs are [on, off, off]. 
 * 
 * So you should return 1, because there is only one bulb is on.
 */

// Math solution
// In order for a bulb to be on, it will need to be toggled an odd number of times.
// A bulb n will be toggled for every one of its divisors.
// Therefore, a bulb will be on if it has an odd number of divisors.
// Only a square number will have odd number of divisors(pairs of divisors, and one root).
// So the number of bulbs that are on is the number of square numbers in [1, n],
// and that number is sqrt(n).
public class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}

// Brute force solution
// perform the exact process as described in the problem
// Time limit exceeded
public class Solution {
    public int bulbSwitch(int n) {
        boolean[] bulbs = new boolean[n];
        
        for (int i = 1; i <= n; i++) {
            for (int j = -1; j < n; j+=i) {
                if (j >= 0) {
                    bulbs[j] = !bulbs[j];
                }
            }
        }
        
        int counter = 0;
        for (boolean bulb : bulbs) {
            if (bulb) counter++;
        }
        
        return counter;
    }
}