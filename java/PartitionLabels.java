/*
	A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

	Example 1:
	Input: S = "ababcbacadefegdehijhklij"
	Output: [9,7,8]
	Explanation:
	The partition is "ababcbaca", "defegde", "hijhklij".
	This is a partition so that each letter appears in at most one part.
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	Note:

	S will have length in range [1, 500].
	S will consist of lowercase letters ('a' to 'z') only.
 */

// Solution 2: Greedy
// First find positions of last occurrences
// Then use two pointers to scan through the array.
//
// Time: O(n)
// Space: O(n)
// 08/22/2018

class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] ends = new int[26];    // record the position of last occurrence
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            ends[c - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, ends[S.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            } 
        }
        return res;
    }
}

// Solution 1: Merge intervals
// Convert each characters to an interval.
// Merge all overlapping intervals.
// 
// Time: O(n + nlogn) = O(nlogn)
// Space: O(n)
// 08/22/2018

class Solution {
    class Interval implements Comparable<Interval> {
        char c;
        int start;
        int end;
        public Interval(char c, int start, int end) {
            this.c = c;
            this.start = start;
            this.end = end;
        }
        public int compareTo(Interval other) {
            return this.start - other.start;
        }
    }
    
    public List<Integer> partitionLabels(String S) {
        HashMap<Character, Interval> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new Interval(c, i, i));
            } else {
                map.get(c).end = i;
            }
        }
        List<Interval> sorted = new ArrayList<>(map.values());
        Collections.sort(sorted);
        List<Integer> res = new ArrayList<>();
        Interval prev = null;
        for (int i = 0; i < sorted.size(); i++) {
            if (prev == null) {
                prev = sorted.get(i);
            } else if (prev.end >= sorted.get(i).start) {
                prev.end = Math.max(prev.end, sorted.get(i).end);
            } else {
                res.add(prev.end - prev.start + 1);
                prev = sorted.get(i);
            }
        }
        res.add(prev.end - prev.start + 1);
        return res;
    }
}