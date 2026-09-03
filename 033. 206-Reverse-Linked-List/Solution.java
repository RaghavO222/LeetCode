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
    public static ListNode recList(ListNode head){
        // Base case: end of the list
        if(head == null || head.next == null){
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode newhead = recList(head.next);
        
        // Reverse the pointer of the next node to point back to the current node
        head.next.next = head;
        
        // Sever the forward pointer to prevent cycles
        head.next = null;
        
        return newhead;
    }
    
    public ListNode reverseList(ListNode head) {
        return recList(head);   
    }
}
