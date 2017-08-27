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
// faster
// time: O(n), space: O(1)
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode p = head, next;
        while (p != null) {     // step 1: insert a copy node after each original node
            next = p.next;
            p.next = new RandomListNode(p.label);
            p.next.next = next;
            p = next;
        }
        
        p = head;
        while (p != null) {     // step 2: copy random pointers
            if (p.random != null) p.next.random = p.random.next;
            p = p.next.next;
        }
        
        p = head;
        RandomListNode dummyHead = new RandomListNode(0);
        RandomListNode copy = dummyHead;
        while (p != null) {     // step 3: separate two lists
            next = p.next.next;     // save original next
            // add copied nodes to copy list
            copy.next = p.next;
            copy = copy.next;
            // restore the original list
            p.next = next;
            p = next;
        }
        return dummyHead.next;
    }
}

// Solution 1: HashMap approach
// first iteration, use HashMap to store <original, copy> pair
// then connects copies in the second iteration
// Time: O(n), space: O(n)
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
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