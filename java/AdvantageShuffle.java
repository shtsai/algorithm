/*
	Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

	Return any permutation of A that maximizes its advantage with respect to B.

	 

	Example 1:

	Input: A = [2,7,11,15], B = [1,10,4,11]
	Output: [2,11,7,15]
	Example 2:

	Input: A = [12,24,8,32], B = [13,25,32,11]
	Output: [24,32,8,12]
	 

	Note:

	1 <= A.length = B.length <= 10000
	0 <= A[i] <= 10^9
	0 <= B[i] <= 10^9
 */

// Solution 1:
// First sort two lists A and B.
// Then iterate through A and beat as many as Bs as possible.
// Finally append unused As to the end.
// The difficult part is to keep track of the original indice.
//
// Time: O(nlogn)
// Space: O(n)
// 07/14/2018

class Solution {
    private class Entry implements Comparable<Entry> {
        int value;
        int index;
        public Entry(int value, int index) {
            this.value = value;
            this.index = index;
        }
        
        public int compareTo(Entry other) {
            return this.value - other.value;
        }
    }
    public int[] advantageCount(int[] A, int[] B) {
        Entry[] A2 = new Entry[A.length];
        Entry[] B2 = new Entry[B.length];
        
        for (int i = 0; i < A.length; i++) {
            A2[i] = new Entry(A[i], i);
            B2[i] = new Entry(B[i], i);
        }
        
        Arrays.sort(A2);
        Arrays.sort(B2);
        
        List<Entry> res = new ArrayList<>();
        Queue<Entry> unused = new LinkedList<>();
        int[] array = new int[A.length];
        Arrays.fill(array, -1);
        int bIndex = 0, endIndex = A.length - 1;
        for (int i = 0; i < A.length; i++) {
            if (A2[i].value > B2[bIndex].value) {
                array[B2[bIndex].index] = A2[i].value;
                bIndex++;
            } else {
                unused.offer(A2[i]);
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == -1) {
                array[i] = unused.poll().value;
            }
        }
        return array;
    }
}