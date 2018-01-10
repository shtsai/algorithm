/*
    Given a non-empty array of integers, return the k most frequent elements.

    For example,
    Given [1,1,1,2,2,3] and k = 2, return [1,2].

    Note: 
    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

// Solution 4: Selection algorithm
// First create entry object for each number and store their frequency.
// Then put them in a list and perform selection algorithm to sort first K based on count.
// Time: O(n) - selection algorithm O(n)
// Space: O(n)
// 01/09/2018
class Solution {
    class Entry {
        int value, count;
        public Entry(int value) {
            this.value = value;
            count = 0;
        }
    }
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Entry> map = new HashMap<>();
        List<Entry> list = new ArrayList<>();
        for (int n : nums) {       // build frequency map and list
            if (!map.containsKey(n)) {
                map.put(n, new Entry(n));
                list.add(map.get(n));
            }
            map.get(n).count++;
        }
        select(list, 0, list.size()-1, k);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).value);
        }
        return res;
    }
    
    public void select(List<Entry> list, int start, int end, int k) {
        while (start < end) {
            int p = partition(list, start, end);
            if (p == k) {
                return;
            } else if (p > k) {
                end = p - 1;
            } else {
                start = p + 1;
            }
        }
    }
    
    public int partition(List<Entry> list, int start, int end) {
        if (start == end) return start;
        int pivot = list.get(start).count;
        int left = start + 1, right = end;
        while (true) {
            while (left < end && list.get(left).count > pivot) left++;
            while (right > start && list.get(right).count <= pivot) right--;
            if (left < right) {
                swap(list, left, right);
            } else {
                break;
            }
        }
        swap(list, start, right);
        return right;
    }
    
    public void swap(List<Entry> list, int a, int b) {
        Entry tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}

// Solution 3: Bucket sort
// First get frequency.
// Then sort numbers into buckets according to their frequency.
// Finally, get top K frequenct elements.
// Time: O(n)
// Space: O(n)
// 01/09/2018
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();   // build frequency map
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                map.put(n, map.get(n) + 1);
            }
        }
        
        List<Integer>[] bucket = new List[nums.length+1];  // add to buckets
        for (int n : map.keySet()) {
            int freq = map.get(n);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(n);
        }
        
        List<Integer> res = new ArrayList<>();        // get top k
        int count = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
                count += bucket[i].size();
                if (count >= k) break;
            }
        }
        return res;
    }
}

// Solution 2: Custom class + prioirity queue
// Time: O(klogn)
// Space: O(n)
// 01/09/2018

class Solution {
    class Entry implements Comparable<Entry>{
        int value, count;
        public Entry(int value) {
            this.value = value;
            count = 0;
        }
        public int compareTo(Entry other) {
            return other.count - this.count;
        }
    }
    
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Entry> map = new HashMap<>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, new Entry(n));
            }
            map.get(n).count++;
        }
        
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        for (Entry e : map.values()) {
            pq.offer(e);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll().value);
        }
        
        return res;
    }
}

// Solution 1: Frequency map + priority queue
// Create a frequency map, and add frequency into priority queue.
// Priority queue will put the most frequent element on the top.
// To get top K, poll() from the priority queue k times.
//
// Time: O(n + klogn) - k calls to poll(logn each)
// Space: O(n)
// 10/18/2017

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>> () {
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(e);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
}