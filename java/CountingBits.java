/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 */


public class Solution {
    public int[] countBits(int num) {
        int[] array = new int[num + 1];
        array[0] = 0;
        for (int i = 1; i <= num; i++) {
            
            // use the idea of dynamic programming
            // the current answer is based on the answer of previous smaller problem
            // only need to check if the last bit is 1
            array[i] = array[i >> 1] + (1 & i);
        }
        return array;
    }
}