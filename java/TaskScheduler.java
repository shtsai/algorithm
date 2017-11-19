/*
    Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

    However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

    You need to return the least number of intervals the CPU will take to finish all the given tasks.

    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
    Note:
    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
 */

// Solution 2: Count empty slots
//
// A | B | C | D |  |
// A | B | C | D |  |
// A | B | C |   |  |
// A | B |   
//
// First get frequency of each task, and sort them in descending order.
// The max possible number of empty slot is (max - 1) * n.
// Then we try to fill other tasks into the empty slot, and return 
// the number of remaining slots.
// If the number of remaining empty slots is less than 0, that means
// we don't need empty slots. Simply return the length of the task array.
// Else, return # of empty slots + length of task array
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Integer[] freq = new Integer[26];
        Arrays.fill(freq, 0);
        for (char t : tasks) {
            freq[t - 'A']++;
        }
        Arrays.sort(freq, Collections.reverseOrder());
        int max = freq[0]-1;
        int slots = max * n;
        for (int i = 1; i < 26; i++) {
            slots -= Math.min(freq[i], max);
        }
        return slots < 0 ? tasks.length : slots + tasks.length;
    }
}

// Solution 1: priority queue
// First get frequency of each task, then put them in the priority queue.
// While there are unfinished tasks, we start a new iteration and do as
// much as tasks we can. Increment the time counter accordingly.
// After we are doing with the current iteration, add unfinished tasks
// into the priorityqueue and repeat.
//
// Time: O(t) - t is the total time required
// Space: O(1) - only 26 types of tasks
// 11/18/2017
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] freq = new int[26];
        for (char t : tasks) {
            freq[t - 'A']++;
        }
        for (int f : freq) {
            if (f > 0) pq.offer(f);
        }
        while (!pq.isEmpty()) {
            int i = 0;
            List<Integer> tmp = new ArrayList<>();
            while (i <= n) {
                if (!pq.isEmpty()) {
                    int todo = pq.poll();
                    if (todo > 1) tmp.add(todo-1);
                }
                i++;
                count++;
                if (pq.isEmpty() && tmp.size() == 0) return count;
            }
            for (int t : tmp) {
                pq.offer(t);
            }
        }
        return count;
    }
}