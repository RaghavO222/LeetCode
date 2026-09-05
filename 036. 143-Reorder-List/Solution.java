/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // Unused recursive reversal method (kept for reference)
    public static ListNode recList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = recList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    
    public void reorderList(ListNode head) {
        // 1. Find the middle
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        ListNode curr = slow.next;
        slow.next = null; // Sever the first half
        
        ListNode prev = null;
        ListNode next = null;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 3. Merge the two halves
        ListNode lh = head;
        ListNode rh = prev;
        ListNode nxtL, nxtR;

        while(lh != null && rh != null){
            nxtL = lh.next;
            lh.next = rh;

            nxtR = rh.next;
            rh.next = nxtL;

            rh = nxtR;
            lh = nxtL;
        }
    }
}
