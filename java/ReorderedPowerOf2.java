/*
	Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

	Return true if and only if we can do this in a way such that the resulting number is a power of 2.

	 

	Example 1:

	Input: 1
	Output: true
	Example 2:

	Input: 10
	Output: false
	Example 3:

	Input: 16
	Output: true
	Example 4:

	Input: 24
	Output: false
	Example 5:

	Input: 46
	Output: true
	 

	Note:

	1 <= N <= 10^9
 */

// Solution 1:
// First find all powers of 2.
// Then find all digits of N.
// Then permute digits of N and check if any permutation is power of 2.
// Time: O(n!) - permutation, n:number of digits of N
// Space: O(n) - store digits, call stack
// 07/14/2018

class Solution {
    public boolean reorderedPowerOf2(int N) {
        HashSet<Integer> powerOf2 = new HashSet<>();
        int value = 1;
        while (value <= 1000000000) {
            powerOf2.add(value);
            value *= 2;
        }
        
        ArrayList<Integer> digits = getDigits(N);
        return permute(digits, powerOf2, 0);
    }
    
    private boolean permute(ArrayList<Integer> digits, HashSet<Integer> powersOf2, int current) {
        if (digits.size() == 0) {
            return powersOf2.contains(current);
        }
        for (int i = 0; i < digits.size(); i++) {
            int digit = digits.get(i);
            if (current == 0 && digit == 0) {
                continue;
            }
            digits.remove(i);
            if (permute(digits, powersOf2, current * 10 + digit)) {
                return true;
            }
            digits.add(i, digit);
        }
        return false;
    }
    
    private ArrayList<Integer> getDigits(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        while (N > 0) {
            int digit = N % 10;
            res.add(digit);
            N = N / 10;
        }
        return res;
    }
}