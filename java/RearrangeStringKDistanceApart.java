/*
	Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

	All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

	Example 1:
	Input: s = "aabbcc", k = 3
	Output: "abcabc" 
	Explanation: The same letters are at least distance 3 from each other.
	
	Example 2:
	Input: s = "aaabc", k = 3
	Output: "" 
	Explanation: It is not possible to rearrange the string.
	
	Example 3:
	Input: s = "aaadbbcc", k = 2
	Output: "abacabcd"
	Explanation: The same letters are at least distance 2 from each other.
 */

// Solution 1: PriorityQueue
// Try to add characters with more counts first.
// Time: O(n)
// Space: O(n)
// 09/09/2018
class Solution {
    class Entry implements Comparable<Entry> {
        char c;
        int count;
        public Entry(char c, int count) {
            this.c = c;
            this.count = count;
        }
        
        public int compareTo(Entry other) {
            if (other.count != this.count) {
                return other.count - this.count;
            } else {
                return this.c - other.c;
            }
        }
    }
    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                pq.offer(new Entry((char) ('a' + i), freq[i]));
            }
        }
        StringBuilder sb = new StringBuilder();
        int count = s.length();
        while (count > 0) {
            int cur = k;
            List<Entry> temp = new ArrayList<>();
            while (cur > 0) {
                if (pq.isEmpty()) { // impossible to rearrange
                    if (temp.isEmpty()) {
                        return sb.toString();
                    } else {
                        return "";
                    }
                } else {
                    Entry e = pq.poll();
                    sb.append(e.c);
                    e.count--;
                    if (e.count > 0) {
                        temp.add(e);
                    }
                    cur--;
                    count--;
                }
            }
            pq.addAll(temp);
        }
        return sb.toString();
    }
}