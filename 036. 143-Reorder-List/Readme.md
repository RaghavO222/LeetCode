# 143. Reorder List

**Difficulty:** Medium

## Problem Statement
You are given the head of a singly linked-list. The list can be represented as:
`L0 → L1 → … → Ln-1 → Ln`

Reorder the list to be on the following form:
`L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …`

You may not modify the values in the list's nodes. Only nodes themselves may be changed.

## Intuition & Approach
To achieve this specific folding pattern, we can break the problem down into three distinct phases:
1. **Find the Middle:** We use the Fast and Slow pointer technique. By the time the `fast` pointer reaches the end, the `slow` pointer will be exactly at the midpoint of the list.
2. **Reverse the Second Half:** We sever the list at the midpoint (`slow.next = null`). Then, we take the entire second half of the list and reverse it using the standard iterative approach (`prev`, `curr`, `next`). *(Note: While your code includes a recursive reversal method `recList`, the actual `reorderList` method efficiently uses the iterative approach!)*
3. **Merge the Two Halves:** We now have two separate lists: the first half (moving forward) and the reversed second half (moving backward from the original tail). We simply iterate through both, alternating their pointers to weave them together.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. Finding the middle takes $O(N/2)$, reversing the second half takes $O(N/2)$, and merging takes $O(N/2)$. Overall, this simplifies to linear time.
* **Space Complexity:** $O(1)$
  We only manipulate existing pointers, allocating no new nodes or using any recursion stack.

## Java Solution

```java
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
