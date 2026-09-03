# 141. Linked List Cycle

**Difficulty:** Easy

## Problem Statement
Given `head`, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Return `true` if there is a cycle in the linked list. Otherwise, return `false`.

## Intuition & Approach
The most efficient way to detect a cycle in a sequence is using **Floyd's Tortoise and Hare algorithm** (also known as the Fast and Slow pointer technique). 

Imagine two runners on a track. If the track is a straight line, the fast runner will simply reach the finish line first. But if the track is a loop (a cycle), the fast runner will eventually lap the slow runner from behind.

1. **Initialize Pointers:** Start both the `slow` pointer and the `fast` pointer at the `head` of the list.
2. **Traverse at Different Speeds:** Create a loop that continues as long as the `fast` pointer has a valid next step.
    * Move the `slow` pointer by 1 step (`slow = slow.next`).
    * Move the `fast` pointer by 2 steps (`fast = fast.next.next`).
3. **Collision Detection:** After moving, check if `slow == fast`. If they are pointing to the exact same node in memory, the fast pointer has successfully lapped the slow pointer, proving a cycle exists. Return `true`.
4. **End of the Line:** If the `fast` pointer ever reaches `null` (or its next node is `null`), it means the list has a definite end. There is no cycle. Return `false`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. 
  * If there is no cycle, the fast pointer reaches the end in $N/2$ steps. 
  * If there is a cycle, the maximum distance the fast pointer has to "catch up" is the length of the cycle (which is at most $N$). Since the fast pointer closes the gap by 1 step per iteration, it takes at most $N$ iterations to meet.
* **Space Complexity:** $O(1)$
  We only allocate memory for two pointers (`fast` and `slow`), meaning the space required is entirely independent of the linked list's size.

## Java Solution

```java
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
