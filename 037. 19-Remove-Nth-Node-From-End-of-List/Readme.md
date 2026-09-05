# 19. Remove Nth Node From End of List

**Difficulty:** Medium

## Problem Statement
Given the `head` of a linked list, remove the $n^{th}$ node from the end of the list and return its head.

## Intuition & Approach
A naive approach would be to iterate through the list once to find its length, subtract `n` to find the target index, and then iterate through the list a second time to remove it. We can do this in a **Single Pass** using two pointers!

1. **Dummy Node:** We use a dummy node pointing to the head. This elegantly handles edge cases, like when the node to be removed is the very first node in the list.
2. **Create a Gap:** We initialize two pointers, `fPtr` (fast) and `sPtr` (slow), both at the dummy node. We advance `sPtr` forward exactly `n` times. This creates a gap of `n` nodes between the two pointers.
3. **Move Together:** We advance both pointers one step at a time until `sPtr` reaches the very last node. Because of the gap we created, `fPtr` will naturally be pointing to the node *right before* the one we need to delete!
4. **Delete:** We rewire `fPtr.next` to skip the target node (`fPtr.next = fPtr.next.next`).

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes in the list. We only traverse the linked list exactly once.
* **Space Complexity:** $O(1)$
  We only allocate a dummy node and two pointers, keeping memory usage constant.

## Java Solution

```java
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
