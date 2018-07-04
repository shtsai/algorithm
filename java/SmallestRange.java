/*
	You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

	We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

	Example 1:
	Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
	Output: [20,24]
	Explanation: 
	List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
	List 2: [0, 9, 12, 20], 20 is in range [20,24].
	List 3: [5, 18, 22, 30], 22 is in range [20,24].
	Note:
	The given list may contain duplicates, so ascending order means >= here.
	1 <= k <= 3500
	-105 <= value of elements <= 105.
	For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
 */

// Solution 2: PriorityQueue
// SImilar to solution 1, but use a priority queue to maintain min value
// need to update max in every iteration.
// Time: O(nlogk) - k:number of lists, n:length of a list
// Space: O(k)
// 07/04/2018
class Solution {
    private class ListEntry implements Comparable<ListEntry> {
        int listIndex;
        int entryIndex;
        int entryValue;

        public ListEntry(int listIndex, int entryIndex, int entryValue) {
            this.listIndex = listIndex;
            this.entryIndex = entryIndex;
            this.entryValue = entryValue;
        }
        
        public int compareTo(ListEntry other) {
            return this.entryValue - other.entryValue;
        }
        
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<ListEntry> pq = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        int[] res = new int[2];
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            max = Math.max(max, value);
            pq.offer(new ListEntry(i, 0, value));
        }
        int range = Integer.MAX_VALUE;
        while (true) {
            ListEntry min = pq.poll();
            System.out.println(max + " " + min.entryValue);
            int listIndex = min.listIndex;
            int entryIndex = min.entryIndex;
            if (range > max - min.entryValue) {
                range = Math.min(range, max - min.entryValue);
                res[0] = min.entryValue;
                res[1] = max;
            }
            
            if (entryIndex + 1 == nums.get(listIndex).size()) {
                break;
            }  
            ListEntry newEntry = new ListEntry(listIndex, entryIndex+1, nums.get(listIndex).get(entryIndex + 1));
            max = Math.max(newEntry.entryValue, max);
            pq.offer(newEntry);
        }
        return res;
    }
}

// Solution 1: array of pointers
// Maintain an array of pointers to the number lists.
// During each iteration, compute the current range,
// then increase the index on the list that has the smallest value and repeat.
//
// Time: O(kn) - k:number of lists, n:length of a list
// Space: O(k)
// 07/04/2018
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // initialization
        int[] indices = new int[nums.size()];
        int[] currentNums = new int[nums.size()];
        for (int i = 0; i < indices.length; i++) {
            currentNums[i] = nums.get(i).get(indices[i]);
        }
        int[] init = findRange(currentNums);
        int range = currentNums[init[1]] - currentNums[init[0]];
        int min = currentNums[init[0]], max = currentNums[init[1]], minIndex = init[0];
        
        while (indices[minIndex] < nums.get(minIndex).size()) {
            indices[minIndex]++;
            if (indices[minIndex] >= nums.get(minIndex).size()) {
                break;
            }
            currentNums[minIndex] = nums.get(minIndex).get(indices[minIndex]);
            
            int[] minMax = findRange(currentNums);
            if (currentNums[minMax[1]] - currentNums[minMax[0]] < range) {
                min = currentNums[minMax[0]];
                max = currentNums[minMax[1]];
                range = max - min;
            }
            minIndex = minMax[0];
        }
        
        return new int[] {min, max};
    }

    // find the indices of min and max of the given array
    private int[] findRange(int[] nums) {
        int[] res = new int[2];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < min) {
                min = num;
                res[0] = i;
            } 
            if (num > max) {
                max = num;
                res[1] = i;
            } 
        }
        return res;
    }
}