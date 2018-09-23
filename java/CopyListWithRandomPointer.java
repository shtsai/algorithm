/*
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

// Solution 2:
// Create copy node right after each original node
// Time: O(n)
// Space: O(1)
// 09/23/2018
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode p = head;
        // 1. Create clone node
        while (p != null) {
            RandomListNode clone = new RandomListNode(p.label);
            clone.next = p.next;
            p.next = clone;
            p = clone.next;
        }
        
        // 2. Copy random node
        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        
        // 3. Concatenate clone list
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode prev = dummy;
        p = head;
        while (p != null) {
            prev.next = p.next;
            prev = prev.next;
            p.next = p.next.next;
            p = p.next;
        }
        return dummy.next;
    }
}

// Solution 1: HashMap approach
// first iteration, use HashMap to store <original, copy> pair
// then connects copies in the second iteration
// Time: O(n)
// Space: O(n)
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        while (node != null) {      // create copy for each node
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        node = head;
        while (node != null) {      // connect each node with their "next" and "random"
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);       // return the copy of the head
    }
}