/*
	Given a non-empty list of words, return the k most frequent elements.

	Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

	Example 1:
	Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	Output: ["i", "love"]
	Explanation: "i" and "love" are the two most frequent words.
	    Note that "i" comes before "love" due to a lower alphabetical order.
	
	Example 2:
	Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
	Output: ["the", "is", "sunny", "day"]
	Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
	    with the number of occurrence being 4, 3, 2 and 1 respectively.
	Note:
	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	Input words contain only lowercase letters.
	Follow up:
	Try to solve it in O(n log k) time and O(n) extra space.
 */

// Solution 1: Heap
// Time: O(nlogk)
// Space: O(n)
// 09/13/2018

class Solution {
    class Entry implements Comparable<Entry> {
        int count;
        String s;
        public Entry(String s, int count) {
            this.s = s;
            this.count = count;
        }
        public int compareTo(Entry other) {
            if (this.count != other.count) {
                return this.count - other.count;
            } else {
                return other.s.compareTo(this.s);
            }
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        
        for (String word : freq.keySet()) {
            pq.offer(new Entry(word, freq.get(word)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().s);
        }
        return res;
    }
}