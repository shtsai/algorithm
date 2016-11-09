/*
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */

public class Solution {

    // use a fast and slow pointer

    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){  
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;
        
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;              // fast jumps two steps each time,
            slow = slow.next;                   // when fast reaches the end, slow is at the middle
        }
        TreeNode thead = new TreeNode(slow.val);  
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }

    /*
    // convert linked list into an array, then divide and conquer
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        
        p = head;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = p.val;
            p = p.next;
        }
        
        int mid = (0 + len) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = subBST(nums, 0, mid - 1);
        root.right = subBST(nums, mid + 1, len - 1);
        
        return root;
    }
    
    public TreeNode subBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left =subBST(nums, start, mid - 1);
        root.right = subBST(nums, mid + 1, end);
        
        return root;
    }
    */
}