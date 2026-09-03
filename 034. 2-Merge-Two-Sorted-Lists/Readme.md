# 21. Merge Two Sorted Lists

**Difficulty:** Easy

## Problem Statement
You are given the heads of two sorted linked lists `list1` and `list2`.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists. Return the head of the merged linked list.

## Intuition & Approach
Because both input lists are already sorted, we can build the merged list in a single pass by continuously comparing the current nodes of both lists and picking the smaller one.

This solution uses the **Dummy Node** pattern. Dealing with the head of a linked list is often messy (you have to write special `if` statements just to initialize it). A dummy node gives us a safe starting point so all nodes can be treated identically.

1. **Dummy Initialization:** Create a `head` dummy node and a `curr` pointer that starts at this dummy node.
2. **Compare and Splice:** While neither `list1` nor `list2` is empty:
    * Compare `list1.val` and `list2.val`.
    * Point `curr.next` to the node with the smaller value.
    * Advance the pointer of the list you just picked from (`list1 = list1.next` or `list2 = list2.next`).
    * Advance the `curr` pointer forward.
3. **Attach Remainder:** Eventually, one list will run out of nodes before the other. Because the lists are already sorted, we can simply take the remainder of the non-empty list and attach it directly to the end of our merged list (`curr.next = (list1 != null) ? list1 : list2`).
4. **Return:** Return `head.next` (skipping the initial dummy node to return the true start of the merged list).

## Complexity Analysis

* **Time Complexity:** $O(N + M)$
  Where $N$ and $M$ are the lengths of `list1` and `list2`. In the worst case, we traverse almost all nodes in both lists before one becomes null.
* **Space Complexity:** $O(1)$
  We are simply rewiring existing pointers. The only new memory we allocate is the single dummy node, resulting in strictly constant extra space.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(); // Dummy node
        ListNode curr = head;

        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                curr.next = list2;
                list2 = list2.next;
            } else {
                curr.next = list1;
                list1 = list1.next;
            }
            curr = curr.next;
        }
        
        // Attach the remaining nodes of whichever list is not empty
        curr.next = (list1 != null) ? list1 : list2;

        // Return the actual head, skipping the dummy node
        return head.next;
    }
}
