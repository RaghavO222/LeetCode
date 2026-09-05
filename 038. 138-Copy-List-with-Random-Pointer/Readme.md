# 138. Copy List with Random Pointer

**Difficulty:** Medium

## Problem Statement
A linked list of length `n` is given such that each node contains an additional random pointer, which could point to any node in the list, or `null`.

Construct a deep copy of the list and return the head of the copied linked list. You must not use any extra space (like HashMaps) to keep track of the clones.

## Intuition & Approach
The challenge here is that when you clone a node, its random pointer might point to a node that hasn't been cloned yet. The $O(N)$ space solution uses a HashMap to map original nodes to their clones. To do this in $O(1)$ space, we use the **Interweaving approach**.

1. **Clone and Interweave:** Iterate through the original list. For every node, create a clone node and insert it immediately *after* the original node. (e.g., `A → A' → B → B' → C → C'`).
2. **Assign Random Pointers:** Iterate through the interwoven list again. Because every clone is right next to its original, the clone's random pointer is simply the original's random pointer's clone! (`curr.next.random = curr.random.next`).
3. **Unweave:** Iterate through the list one last time to separate the original list and the cloned list back into two distinct chains.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  We make exactly three linear passes over the linked list. 
* **Space Complexity:** $O(1)$
  Excluding the space required to store the actual deep copy (which is required by the problem and thus not counted), we use no extra data structures like HashMaps.

## Java Solution

```java
/*
// Definition for a Node.
class Node { ... }
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;

        Node curr = head;

        // 1. Create interwoven clone nodes
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        curr = head;

        // 2. Assign random pointers to the clones
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node newHead = curr.next;
        Node newCurr = newHead;
        
        // 3. Unweave the lists
        while(curr != null){
            curr.next = newCurr.next;
            curr = curr.next;
            if(curr != null){
                newCurr.next = curr.next;
                newCurr = newCurr.next;
            }
        }

        return newHead;
    }
}
