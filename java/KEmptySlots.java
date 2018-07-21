/*
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 *
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
 *
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
 *
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
 *
 * If there isn't such day, output -1.
 *
 * Example 1:
 * Input:
 * flowers: [1,3,2]
 * k: 1
 * Output: 2
 * Explanation: In the second day, the first and the third flower have become blooming.
 * Example 2:
 * Input:
 * flowers: [1,2,3]
 * k: 1
 * Output: -1
 */

// Solution 5: Find peaks
// First convert `flowers` to `days`
// Then looks for windows [i,j] where i + k + 1 = j
// and for all k > i && k < j, days[k] < days[i] && days[k] < days[j]
//
// Time: O(n)
// Space: O(n)
// 07/21/2018
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }
        int res = Integer.MAX_VALUE;
        int left = 0, right = k + 1;
        while (right < days.length) {
            boolean satisfied = true;
            int i = left + 1;
            for (; i < right; i++) {    // check if all values within this window satisfy condition
                if (days[i] < days[left] || days[i] < days[right]) {
                    satisfied = false;
                    break;
                }
            }
            if (satisfied) {
                res = Math.min(res, Math.max(days[left], days[right]));
            }
            // continue search from the new valley
            left = i;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

// Solution 1: brute force
// Check day by day
// Time limit exceeded
// 09/23/2017
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length == 0) return -1;
        boolean[] slots = new boolean[flowers.length];
        slots[flowers[0]-1] = true;
        for (int i = 1; i < flowers.length; i++) {
            slots[flowers[i]-1] = true;
            int left = -1;
            for (int j = 0; j < slots.length; j++) {
                if (slots[j]) {
                    if (left == -1) {
                        left = j;
                    } else if (j - left == k+1) {
                        return i+1;
                    } else {
                        left = j;
                    }
                }
            }
        }
        return -1;
    }
}
