/*
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, 
 * print the sequence of gray code. A gray code sequence must begin with 0.
 *
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 *
 * 	00 - 0
 * 	01 - 1
 * 	11 - 3
 * 	10 - 2
 */

public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < 1<<n; i++){  // n bit number has 2^n permutation
            list.add(i ^ (i >> 1));   // observe this property
        }
        return list;
    }
}

/*
	a new permutation can be obtained by doing i ^ (i >> 1),  while i increment by 1 in each iteration

	e.g. n = 3

	     i       |  000  |  001  |  010  |  011  |  100  |  101  |  110  | 111  |
	   i >> 1    |  000  |  000  |  001  |  001  |  010  |  010  |  011  | 011  |
	----------------------------------------------------------------------------------
	i ^ (i >> 1) |  000  |  001  |  011  |  010  |  110  |  111  |  101  | 100  |


*/