# 287. Find the Duplicate Number

**Difficulty:** Medium

## Problem Statement
Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive. There is only one repeated number in `nums`, return this repeated number.

You must solve the problem without modifying the array `nums` and uses only constant extra space.

## Intuition & Approach
The constraints (no modification, constant space, numbers strictly between `1` and `n`) point directly to a very clever realization: **We can treat the array as a Linked List.**

Because values are in the range `[1, n]`, every value in the array points to a valid index within the array. Multiple elements containing the same value means multiple indices "point" to the same destination index. This creates a cycle! We can use **Floyd's Tortoise and Hare algorithm** to find the entrance to this cycle.

1. **Phase 1 (Intersection):** Initialize a `slow` and `fast` pointer at index `0`. Move `slow` by 1 step (`nums[slow]`) and `fast` by 2 steps (`nums[nums[fast]]`). Because there is a cycle, they will eventually meet.
2. **Phase 2 (Find Entrance):** Reset the `slow` pointer back to index `0` (the "head" of our virtual linked list), but leave `fast` at the intersection point. Now, move both pointers exactly 1 step at a time. The exact index where they meet again is the entrance to the cycle, which is our duplicate number!

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Finding the intersection and then finding the cycle entrance both take strictly linear time.
* **Space Complexity:** $O(1)$
  We only allocate two integer pointers. No sets or auxiliary arrays are used.

## Java Solution

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        
        // Phase 1: Find the intersection point
        do {
            slow = nums[slow]; // Move 1 step
            fast = nums[nums[fast]]; // Move 2 steps
        } while(slow != fast);

        // Phase 2: Find the entrance to the cycle (the duplicate number)
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
