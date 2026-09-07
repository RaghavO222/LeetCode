# 25. Reverse Nodes in k-Group

**Difficulty:** Hard

## Problem Statement
Given the `head` of a linked list, reverse the nodes of the list `k` at a time, and return the modified list.

`k` is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

## Intuition & Approach
Reversing a linked list is a standard operation, but doing it in chunks of `k` requires careful pointer management. This solution uses a **Recursive Approach** to handle one group of `k` nodes at a time, trusting the recursion to handle the rest of the list.

1. **Calculate Length:** First, we traverse the entire list once to find its total length. This is crucial because the problem states we should *not* reverse a group if it has fewer than `k` nodes left.
2. **Recursive Base Case:** In our recursive function `recRevList`, we check if the remaining `length` is less than `k`. If it is, we simply return the `head` untouched.
3. **Reverse `k` Nodes:** We use the standard iterative linked list reversal technique (`prev`, `curr`, `next`) but we restrict it to run exactly `k` times. 
    * After this loop, `prev` points to the *new head* of this reversed segment.
    * The original `head` now acts as the *new tail* of this reversed segment.
    * `curr` (and `next`) point to the start of the *next* segment to be processed.
4. **Recursive Leap:** We set `head.next` (our new tail) to the result of `recRevList(next, k, length - k)`. This elegantly chains our freshly reversed group to whatever the recursively reversed remainder of the list turns out to be.
5. **Return:** We return `prev`, which becomes the finalized head of the modified list structure.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. We traverse the list once to get the length ($O(N)$), and then we traverse the list a second time to reverse the chunks ($O(N)$). $O(2N)$ simplifies to $O(N)$.
* **Space Complexity:** $O(N/k)$
  We process the list in recursive chunks. For a list of size $N$, there will be $N/k$ recursive calls added to the system call stack.

## Java Solution

```java
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
