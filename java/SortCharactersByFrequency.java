/*
    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:

    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:

    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:

    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.
 */

// Solution 3: Frequency Map + Priority Queue
// First get frequency map.
// Then create a priority queue that sorts Map.Entry in descending order
// of values. In this way, each poll() of priority queue will return the
// character with max frequency in the queue.
// We can then build the result.
// Time: O(n)
// Space: O(n)
// 10/18/2017
    
class Solution {
    public String frequencySort(String s) {
        // get frequency map
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        for (Map.Entry e : map.entrySet()) {
            pq.offer(e);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            for (int i = 0; i < (int) e.getValue(); i++) {
                sb.append(e.getKey());
            }
        }
        return sb.toString();
    }
}

// Solution 2: 
// First get the frequency map.
// Then, for each frequency, build a list of characters with that frequency
// Lastly, build result from the list.
// Time: O(n) - three passes
// Space: O(n) - HashMap, array, list
// 10/18/2017

class Solution {
    public String frequencySort(String s) {
        // get frequency map
        HashMap<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        // build lists for each frequency
        ArrayList<Character>[] list = new ArrayList[s.length()+1];
        for (Character c : map.keySet()) {
            int freq = map.get(c);
            if (list[freq] == null) {
                list[freq] = new ArrayList<>();
            }
            list[freq].add(c);
        }
        
        // Build string
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i >= 0; i--) {
            if (list[i] == null) continue;
            for (Character c : list[i]) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}    

// Solution 1: Use FreqNode for each frequency
// Similar to the idea of LRU
// Have a doubly linked sorted list of FreqNode,
// each node indicates a frequency level and a set of 
// characters that belongs to this level.
// When a char is first found, add it right after the head with freq 1.
// When new occurence found, move the node to next freq level.
// Finally, retrieve the result by scanning through the list
// from tail to head.
//
// Time: O(n) - one pass to add, one pass to retrieve
// Space: O(n) - At most n nodes, and HashMap
// 10/18/2017

class Solution {
    private class FreqNode {
        int freq;
        HashSet<Character> set;
        FreqNode pre, next;
        public FreqNode(int i) {
            freq = i;
            set = new HashSet<>();
        }
    }
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) return s;
        FreqNode head = new FreqNode(0);
        FreqNode tail = new FreqNode(-1);
        head.next = tail;
        tail.pre = head;
        HashMap<Character, FreqNode> map = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                moveToNextLevel(c, map);
            } else {
                addToHead(c, head, map);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        FreqNode p = tail.pre;
        while (p != head) {
            for (Character c : p.set) {
                for (int i = 0; i < p.freq; i++) {
                    sb.append(c);
                }
            }
            p = p.pre;
        }
        return sb.toString();
    }
    
    private void moveToNextLevel(Character c, HashMap<Character, FreqNode> map) {
        FreqNode cur = map.get(c);
        cur.set.remove(c);
        if (cur.next.freq == cur.freq+1) {
            cur.next.set.add(c);
            map.put(c, cur.next);
        } else {
            FreqNode nextFreq = new FreqNode(cur.freq + 1);
            nextFreq.pre = cur;
            nextFreq.next = cur.next;
            nextFreq.pre.next = nextFreq;
            nextFreq.next.pre = nextFreq;
            nextFreq.set.add(c);
            map.put(c, nextFreq);
        }
    }
    
    private void addToHead (Character c, FreqNode head, HashMap<Character, FreqNode> map) {
        if (head.next.freq == 1) {
            head.next.set.add(c);
            map.put(c, head.next);
        } else {
            FreqNode Freq1 = new FreqNode(1);
            Freq1.pre = head;
            Freq1.next = head.next;
            Freq1.pre.next = Freq1;
            Freq1.next.pre = Freq1;
            Freq1.set.add(c);
            map.put(c, Freq1);
        }
    }
}