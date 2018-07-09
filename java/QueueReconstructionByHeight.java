/*
	Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

	Note:
	The number of people is less than 1,100.


	Example

	Input:
	[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

	Output:
	[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

// Solution 1: Sort + insert
// First sort the array such that higher people come first.
// It two people has the same height, the person with smaller k-value comes first.
// Finally, we just need to insert people using their k-value as index.
// 
// Time: O(nlogn + n^2)
// Space: O(n)
// 07/09/2018
	
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare (int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return b[0] - a[0];
                }
            }
        });
        
        List<int[]> res = new LinkedList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        int[][] resArray = new int[people.length][2];
        return res.toArray(resArray);
    }
}