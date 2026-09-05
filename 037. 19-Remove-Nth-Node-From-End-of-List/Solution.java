/**
 * Definition for singly-linked list.
 * public class ListNode { ... }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode fPtr = dummy;
        ListNode sPtr = dummy;

        // Create a gap of 'n' between the two pointers
        for(int i = 0; i < n; i++){
            sPtr = sPtr.next;
        }

        // Move both pointers until the fast one hits the end
        while(sPtr.next != null){
            fPtr = fPtr.next;
            sPtr = sPtr.next;
        }
        
        // Remove the target node
        fPtr.next = fPtr.next.next;
        
        return dummy.next;
    }
}
