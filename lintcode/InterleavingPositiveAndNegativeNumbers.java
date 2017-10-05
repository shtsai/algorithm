/*
    Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

     Notice:
        You are not necessary to keep the original order of positive integers or negative integers.

    Example:
        Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.
     */

// Solution 1: two pointers
// First use two pointers to divide positive and negative numbers in the array.
// Then start the new array with the type of number that has a higher count.
// Use similar two pointers approach, swap numbers to their correct position.
// Time: O(n)
// Space: O(1)
// 10/05/2017

public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] A) {
        // move all negative numbers to the left
        // and all positive numbers to the right
        int left = 0, right = A.length-1;
        while (true) {
            while (left < A.length && A[left]<0) left++;
            while (right >= 0 && A[right] > 0) right--;
            if (left < right) {
                swap(A, left, right);
            } else {
                break;
            }
        }

        int negCount = right + 1; // right is last negative
        int posCount = A.length - right - 1;
        
        int neg = 0, pos = 1;   // default start with negative number 
        if (posCount > negCount) {  // if has more postives than negatives
            neg = 1;                // switch indices to start with positive number
            pos = 0;                // and reverse the whole array
            int a = 0, b = A.length-1;
            while (a < b) {
                swap(A, a, b);
                a++;
                b--;
            }
        }
        
        // use two pointers to find misplaced positives and negatives
        // and swap them.
        while (true) {
            while (neg < A.length && A[neg] < 0) neg += 2;
            while (pos < A.length && A[pos] > 0) pos += 2;
            if (neg < A.length && pos < A.length) {
                swap(A, neg, pos);
            } else {
                break;
            }
        }
    }
    
    private void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}