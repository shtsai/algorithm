/*
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * LFUCache cache = new LFUCache( 2 /* capacity */ );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 */


// Use two HashMaps, one for key value pairs, 
// the other for key and its corresponding count node.
// Within each count node, we have a Linked Hash Set containing
// the keys that have current count. 
// The reason to use Linked Hash Set is because search and remove is 
// fast. In addition, the elements in the set are ordered, behave
// in a FIFO manner, which is useful when we need to decide which key
// to evict.
//
// reference: https://discuss.leetcode.com/topic/69137/java-o-1-accept-solution-using-hashmap-doublelinkedlist-and-linkedhashset
class LFUCache {
    private class Node {
        int count;
        LinkedHashSet<Integer> list;
        Node pre, next;
        public Node(int count) {
            this.count = count;
            list = new LinkedHashSet<Integer>();
        }
    }
    
    int capacity, size;
    HashMap<Integer, Integer> keyValue;
    HashMap<Integer, Node> countMap;
    Node head;    // dummy head for the count list
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        keyValue = new HashMap<Integer, Integer>();
        countMap = new HashMap<Integer, Node>();
        head = new Node(-1);
    }
    
    public int get(int key) {
        if (keyValue.containsKey(key)) {
            increaseCount(key);
            return keyValue.get(key);
        } else {    // does not contain key
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) return;
        if (keyValue.containsKey(key)) {
            keyValue.put(key, value);
            increaseCount(key);
            return;
        }
        if (size >= capacity)  {    // size exceeds limit
            removeLFU();
            size--;
        }
        keyValue.put(key, value);
        size++;
        addToFront(key);    // add this key to the front of the list
    }
    
    /* Move a key to its next count level */
    private void increaseCount(int key) {
        Node cur = countMap.get(key);  // retrieve the current count level
        cur.list.remove(key);   // remove this key from the current count level
        // add this key to next level
        if (cur.next == null) {     // cur is the last node in the list
            cur.next = new Node(cur.count+1);   // create a new node for level+1
            cur.next.pre = cur;
        } else if (cur.next.count > cur.count+1) { // next node is not level + 1
            Node newNode = new Node(cur.count+1);
            newNode.pre = cur;
            newNode.next = cur.next;
            cur.next.pre = newNode;
            cur.next = newNode;
        }   // else level + 1 already exists
        cur.next.list.add(key);
        countMap.put(key, cur.next);
        if (cur.list.size() == 0) {
            removeNode(cur);
        }
    }
    
    /* This function adds a key to the front of the list,
       meaning that it has an initial count of 0    */
    private void addToFront(int key) {
        if (head.next == null) {    // nothing in the list yet
            head.next = new Node(0);
            head.next.pre = head;
        } else if (head.next.count != 0) {  // not count 0
            Node newNode = new Node(0);
            newNode.next = head.next;
            newNode.pre = head;
            head.next.pre = newNode;
            head.next = newNode;
        }
        head.next.list.add(key);
        countMap.put(key, head.next);
    }
    
    /* remove a node from the list */
    private void removeNode(Node toRemove) {
        Node p = head;
        if (toRemove.next == null) {
            toRemove.pre.next = null;
            return;
        }
        toRemove.pre.next = toRemove.next;
        toRemove.next.pre = toRemove.pre;
        
    }
    
    /* get the key to remove */
    private void removeLFU() {
        if (head.next == null) return;
        Node leastCount = head.next;
        int keyToRemove = leastCount.list.iterator().next();
        leastCount.list.remove(keyToRemove);
        keyValue.remove(keyToRemove);
        countMap.remove(keyToRemove);
        if (leastCount.list.size() == 0) removeNode(leastCount);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */