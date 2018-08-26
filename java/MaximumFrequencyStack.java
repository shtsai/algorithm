/*
	Implement FreqStack, a class which simulates the operation of a stack-like data structure.

	FreqStack has two functions:
	- push(int x), which pushes an integer x onto the stack.
	- pop(), which removes and returns the most frequent element in the stack.
	If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
	 
	Example 1:
	Input: 
	["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
	[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
	Output: [null,null,null,null,null,null,null,5,7,5,4]
	
	Explanation:
	After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

	pop() -> returns 5, as 5 is the most frequent.
	The stack becomes [5,7,5,7,4].

	pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
	The stack becomes [5,7,5,4].

	pop() -> returns 5.
	The stack becomes [5,7,4].

	pop() -> returns 4.
	The stack becomes [5,7].
	 
	Note:
	- Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
	- It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
	- The total number of FreqStack.push calls will not exceed 10000 in a single test case.
	- The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
	- The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */

// Solution 2: HashMap + Stack
// Use a HashMap to keep track of the prequency for each char.
// Use a variable to keep track of the current max frequency.
// Store the numbers of each frequency in stacks.
//
// Time: push() - O(1)
//       pop() - O(1)
// Space: O(n)
// 08/26/2018

class FreqStack {
    HashMap<Integer, Integer> freq;
    HashMap<Integer, Stack<Integer>> occurrence;
    int maxFreq;
    
    public FreqStack() {
        freq = new HashMap<>();
        occurrence = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        if (f > maxFreq) {
            maxFreq = f;
        }
        
        occurrence.computeIfAbsent(f, y -> new Stack<>()).push(x);
    }
    
    public int pop() {
        int res = occurrence.get(maxFreq).pop();
        freq.put(res, freq.get(res) - 1);
        if (occurrence.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return res;
    }
}

// Solution 1: Priority Queue + HashMap
// Priority queue: sort by frequency and last occurence
// HashMap for quick look up
// Time: push() - O(n)
//       pop() - O(logn)
// Space: O(n)
// 08/25/2018

class FreqStack {
    class Entry implements Comparable<Entry> {
        int val;
        int freq;
        Stack<Integer> ts;
        public Entry(int val) {
            this.val = val;
            ts = new Stack<Integer>();
        }
        
        public int compareTo(Entry other) {
            if (this.freq != other.freq) {
                return other.freq - this.freq;      // descending order by frequency
            } else {
                return other.ts.peek() - this.ts.peek();    // descending order by timestamp
            }
        }
    }
    
    PriorityQueue<Entry> pq;
    HashMap<Integer, Entry> map;
    int time;
    public FreqStack() {
        pq = new PriorityQueue<>();
        map = new HashMap<Integer, Entry>();
        time = 0;
    }
    
    public void push(int x) {
        Entry e;
        if (map.containsKey(x)) {
            e = map.get(x);
            pq.remove(e);
        } else {
            e = new Entry(x);
            map.put(x, e);
        }
        e.freq++;
        e.ts.push(time++);
        pq.offer(e);
    }
    
    public int pop() {
        Entry e = pq.poll();
        
        e.freq -= 1;
        e.ts.pop();
        if (e.freq == 0) {
            map.remove(e.val);
        } else {
            pq.offer(e);
        }
        return e.val;
    }
}

