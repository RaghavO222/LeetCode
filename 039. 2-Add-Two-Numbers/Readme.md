# 2. Add Two Numbers

**Difficulty:** Medium

## Problem Statement
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

## Intuition & Approach
Because the digits are stored in reverse order, the head of the list represents the 1s place, the second node represents the 10s place, etc. This is incredibly convenient because it exactly mimics how we add numbers by hand: starting from the least significant digit and carrying the remainder over to the right.

1. **Dummy Head:** Use a dummy node to easily construct the resulting linked list without writing edge-case initialization logic.
2. **Iterate and Add:** Traverse both lists simultaneously. At each step, calculate the `sum` of `l1.val`, `l2.val`, and the `carry` from the previous addition.
3. **Calculate Carry:** The digit to place in the new node is `sum % 10`. The new carry for the next calculation is `sum / 10`.
4. **Continue:** Keep looping as long as there are nodes left in `l1`, nodes left in `l2`, **or** if there is a leftover `carry` that hasn't been placed into a node yet.

## Complexity Analysis

* **Time Complexity:** $O(\max(N, M))$
  Where $N$ and $M$ are the lengths of `l1` and `l2`. We iterate exactly as many times as the length of the longer list.
* **Space Complexity:** $O(\max(N, M))$
  The length of the new list is at most one node longer than the longest input list (due to a final carry).

## Java Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode { ... }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyhead = new ListNode(0);
        ListNode current = dummyhead;
        int carry = 0;

        // Loop as long as lists have nodes, OR there is a carry left over
        while(l1 != null || l2 != null || carry != 0){
            int sum = carry;

            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummyhead.next;
    }
}
