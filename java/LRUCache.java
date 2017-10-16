/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Solution 1 version 2:
// More natural implementation: add to tail, remove from head (like a queue)
// Also evict before insert, satisfy requirement
// Time: O(1) put, O(1) get
// Space: O(n) - n = capacitiy
// 10/16/2017

class LRUCache {
    private class Node {
        int key;
        int val;
        Node pre;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }
    
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    int size;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node p = map.get(key);
        remove(p);
        addToTail(p);
        return p.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) { // update key
            Node toUpdate = map.get(key);
            toUpdate.val = value;
            remove(toUpdate);
            addToTail(toUpdate);
        } else { // need insert
            if (size == capacity) { // evict before insert
                evict();
            }
            if (size < capacity) {  // check have enough room
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToTail(newNode);
                size++;
            }
        }
    }
    
    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    private void addToTail(Node node) {
        node.pre = tail.pre;
        node.next = tail;
        node.pre.next = node;
        node.next.pre = node;
    }
    
    private void evict() {
        Node toRemove = head.next;
        if (toRemove == tail) return;   // cannot remove dummy tail
        remove(toRemove);
        map.remove(toRemove.key);
        size--;
    }
}

// Solution 1:
// Use a doubly-linked list, add and delete are O(1)
// Use HashMap to enable quick find for a node
// reference: https://discuss.leetcode.com/topic/6613/java-hashtable-double-linked-list-with-a-touch-of-pseudo-nodes
class LRUCache {
    private HashMap<Integer, Node> map;
    private Node head, tail;
    private int size, capacity;
    
    private class Node{
        int key, value;
        Node pre, next;
    }
    private void addToFront(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
    private void delete(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    private Node poll() {
        Node lastNode = tail.pre;
        delete(lastNode);
        return lastNode;
    }
    private void moveToFront(Node node) {
        delete(node);
        addToFront(node);
    }
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        size = 0;
        head = new Node();
        tail = new Node();
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToFront(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node oldNode = map.get(key);
        if (oldNode == null) {
            Node node = new Node();
            node.key = key;
            node.value = value;
            map.put(key, node);
            addToFront(node);
            size++;
            if (size > capacity) {
                Node remove = poll();
                map.remove(remove.key);
                size--;
            }
        } else {
            oldNode.value = value;
            moveToFront(oldNode);
        }
    }
}

