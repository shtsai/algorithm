/*
    Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
    Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
    Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
    Return the minimum integer K such that she can eat all the bananas within H hours.

    Example 1:
    Input: piles = [3,6,7,11], H = 8
    Output: 4

    Example 2:
    Input: piles = [30,11,23,4,20], H = 5
    Output: 30

    Example 3:
    Input: piles = [30,11,23,4,20], H = 6
    Output: 23

    Note:
    1 <= piles.length <= 10^4
    piles.length <= H <= 10^9
    1 <= piles[i] <= 10^9
 */

// Solution 1: Binary search
// Guess on range [0, sum]
// After guess, check if can finish within H hours.
//
// Time: O(nlogm) - n: # of piles, m: total # of bananas
// Space: O(1)
// 07/21/2018

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        long sum = 0;
        for (int p : piles) {
            sum += p;
        }
        long left = 0, right = sum;
        while (left + 1 < right) {
            Long mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H)) {

                right = mid;
            } else {
                left = mid;
            }
        }
        if (canFinish(piles, left, H)) {
            return (int) left;
        } else {
            return (int) left + 1;
        }
    }

    private boolean canFinish(int[] piles, long x, int H) {
        int sum = 0;
        for (int p : piles) {
            sum += Math.ceil((double) p / x);
        }
        return sum <= H;
    }
}
