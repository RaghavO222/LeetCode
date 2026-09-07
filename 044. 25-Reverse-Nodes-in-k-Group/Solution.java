/**
 * Definition for singly-linked list.
 * public class ListNode { ... }
 */
class Solution {
    // Helper method to find the total length of the linked list
    public static int getLen(ListNode head){
        int count = 0;
        while(head != null){
            count++;
            head = head.next;
        }
        return count;
    }
    
    public static ListNode recRevList(ListNode head, int k, int length){
        // Base case: If remaining nodes are less than k, don't reverse
        if(length < k){
            return head;
        }
        
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        int count = 0;

        // Standard reversal, but limited to k nodes
        while(curr != null && count < k){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        
        // head is now the tail of this reversed group. 
        // Connect it to the recursively processed remainder of the list.
        if(next != null){
            head.next = recRevList(next, k, length - k);
        }
        
        // prev is the new head of this reversed group
        return prev;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        int length = getLen(head);
        return recRevList(head, k, length);
    }
}
