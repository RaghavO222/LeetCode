# 206. Reverse Linked List

**Difficulty:** Easy

## Problem Statement
Given the `head` of a singly linked list, reverse the list, and return the reversed list.

## Intuition & Approach
While this problem can be solved iteratively with three pointers, this solution uses a **Recursive Approach**. The beauty of recursion here is that it naturally traverses to the very end of the list and then reverses the pointers sequentially as the call stack unwinds.

1. **Base Case:** If the `head` is `null` (empty list) or `head.next` is `null` (only one node left), we have reached the end of the original list. We return this node, as it will become the `newhead` of our reversed list.
2. **Recursive Leap of Faith:** We recursively call `recList(head.next)`. We trust that this call will reverse the rest of the list and return the new head. 
3. **Reverse the Pointer:** As the call stack unwinds, we are sitting at a `head` node, and `head.next` points to the next node. To reverse the direction, we command the next node to point back at us: `head.next.next = head`.
4. **Sever the Old Link:** We must set our current `head.next = null`. If we don't, we will create a cycle. (Don't worry, if this node isn't the true tail of the reversed list, the previous recursive call will correctly overwrite its `.next` pointer in the next step).
5. **Return:** We continuously bubble up the `newhead` all the way to the top of the initial function call.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes in the linked list. We visit every single node exactly once during the recursive descent.
* **Space Complexity:** $O(N)$
  Unlike the $O(1)$ iterative approach, recursion relies on the system call stack. In the worst-case scenario, the recursion goes $N$ levels deep, requiring linear extra memory.

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
