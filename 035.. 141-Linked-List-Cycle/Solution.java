/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // Traverse while the fast pointer can safely take 2 steps
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            // If they meet, a cycle exists
            if(fast == slow){
                return true;
            }
        }
        
        // Fast pointer reached the end of the list
        return false;
    }
}
