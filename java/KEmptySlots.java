/*
    There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

    Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

    For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

    Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

    If there isn't such day, output -1.

    Example 1:
    Input: 
    flowers: [1,3,2]
    k: 1
    Output: 2
    Explanation: In the second day, the first and the third flower have become blooming.
    Example 2:
    Input: 
    flowers: [1,2,3]
    k: 1
    Output: -1
    Note:
    The given array will be in the range [1, 20000].
 */

// Solution 4: min queue
// Create a days array, where days[i] represents that slot i will open on days[i].
// Maintain a min queue of size k, and we check if min of the window is greater than 
// both neighbors.
// 
// Time: O(n^2) - min-O(1), remove-O(n)
// Space: O(n)
// 07/20/2018

// Solution 3: Tree set, find prev and next
// Use a tree set to store slots in order.
// Every time we add a new slot, we find its left and right slots O(log n)
// and check if the distance is k
// Time: O(nlogn)
// Space: O(n)
// 07/19/2018
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> slots = new TreeSet<>();
        for (int day = 0; day < flowers.length; day++) {
            int f = flowers[day];
            slots.add(f);
            
            Integer prev = slots.lower(f);
            if (prev != null && f - prev - 1 == k) {
                return day + 1;
            }
            Integer next = slots.higher(f);
            if (next != null && next - f - 1 == k) {
                return day + 1;
            }
        }
        return -1;
    }
}    

// Solution 2: Tree set, compare all adjacent
// Use a tree set to store slots in order.
// Every time we add a new slot, we compare all adjacent pairs
// and see if any of them satisfy the condition.
//
// Time: O(n^2) - n for outer loop, and n for iterating the set.
// Space: O(n)
// 07/19/2018

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> slots = new TreeSet<>();
        for (int day = 0; day < flowers.length; day++) {
            slots.add(flowers[day]);
            Integer prev = null;
            for (int s : slots) {
                if (prev == null) {
                    prev = s;
                } else if (s - prev - 1 == k) {
                    return day + 1;
                } else {
                    prev = s;
                }
            }
        }
        return -1;
    }
}

// Solution 1: Brute force
// Maintain a status array.
// Check if condition is met after every update.
// 
// Time: O(n^3) - time limit exceeded
// Space: O(n)
// 07/18/2018

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        boolean[] status = new boolean[flowers.length];
        for (int day = 0; day < flowers.length; day++) {
            status[flowers[day] - 1] = true;
            for (int i = 0; i < flowers.length - k - 1; i++) {
                int j = i + k + 1;
                if (status[i] != true || status[j] != true) {
                    continue;
                }
                boolean allFalse = true;
                for (int s = i + 1; s < j; s++) {
                    if (status[s] != false) {
                        allFalse = false;
                        break;
                    }
                }
                if (allFalse) {
                    return day + 1;
                }
            }
        }
        return -1;
    }
}