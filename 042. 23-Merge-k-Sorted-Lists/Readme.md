# 23. Merge k Sorted Lists

**Difficulty:** Hard

## Problem Statement
You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

## Intuition & Approach
While there are complex ways to weave these lists together in-place by comparing their heads, a perfectly valid (and very readable) brute-force approach is to simply extract all the values, sort them globally, and build a brand new list from scratch.

1. **Extraction:** We create a dynamic `ArrayList`. We loop through every single linked list in the `lists` array, traversing each one and dumping every integer value we find into our `ArrayList`.
2. **Edge Case Handling:** If our `ArrayList` is empty (meaning all input lists were empty), we simply return `null`.
3. **Sort:** We use Java's built-in `Collections.sort()` to organize all the extracted integers into ascending order.
4. **Rebuild:** We initialize a new head node with the first value in our sorted list. Then, we iterate through the rest of the sorted list, creating a brand new `ListNode` for each value and linking them together to form our final answer.

## Complexity Analysis

* **Time Complexity:** $O(N \log N)$
  Where $N$ is the total number of nodes across all `k` linked lists combined. Extracting the values takes $O(N)$ time, and rebuilding the list takes $O(N)$ time. However, sorting the array list takes $O(N \log N)$ time, which dominates the overall runtime.
* **Space Complexity:** $O(N)$
  We allocate $O(N)$ space for the `ArrayList` to hold all the values, and another $O(N)$ space to create the brand new `ListNode` chain for the output. 

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
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        // 1. Extract all values from every linked list
        for(ListNode a : lists){
            ListNode curr = a;

            while(curr != null){
                list.add(curr.val);
                curr = curr.next;
            }
        }

        // 2. Handle edge case of empty input
        if(list.size() == 0){
            return null;
        }

        // 3. Sort all the extracted values globally
        Collections.sort(list);

        // 4. Rebuild a brand new linked list from the sorted values
        ListNode ans = new ListNode(list.get(0));
        ListNode curr = ans;

        for(int i = 1; i < list.size(); i++){
            curr.next = new ListNode(list.get(i));
            curr = curr.next;
        }

        return ans;
    }
}
