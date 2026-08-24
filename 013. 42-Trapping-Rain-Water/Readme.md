# 42. Trapping Rain Water

**Difficulty:** Hard

## Problem Statement
Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

## Intuition & Approach
The core concept is that the amount of water trapped above any specific bar is determined by the tallest bar to its left and the tallest bar to its right. Specifically, the water level above a bar is `Math.min(tallest_left, tallest_right) - current_height`.

A naive approach would be to scan the array to find the max left and max right for *every* element, but that takes $O(N^2)$ time. We can optimize this to a single pass using a **Two-Pointer technique**.

1. **Initialize Variables:** We use two pointers, `start` at the beginning and `end` at the end of the array. We also maintain two variables, `left` and `right`, to track the highest elevation seen so far from the left and from the right.
2. **Track the Bottleneck:** At each step, we update our `left` and `right` maximums. Because water spills over the *shorter* side, the amount of water we can trap is always dictated by the smaller of the two maximums.
3. **Calculate and Move:** 
    * If `left < right`: We know the water level at the `start` index is bounded by `left`. We can confidently calculate the trapped water at `start` by subtracting its height from `left`. Then, we move the `start` pointer forward.
    * If `left >= right`: We know the water level at the `end` index is bounded by `right`. We calculate the trapped water at `end` by subtracting its height from `right`. Then, we move the `end` pointer backward.
4. **Accumulate:** We keep adding these trapped water units to our total `max` (which acts as our accumulator) until the pointers meet.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of elements in the `height` array. We process each element in the array exactly once as the `start` and `end` pointers move toward each other.
* **Space Complexity:** $O(1)$
  We only use a few integer variables (`max`, `left`, `right`, `start`, `end`). No arrays or extra data structures are created, making this the most space-efficient solution possible.

## Java Solution

```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int max = 0; // Acts as our total trapped water accumulator

        int left = 0;  // Max height seen from the left
        int right = 0; // Max height seen from the right

        int start = 0;
        int end = n - 1;

        while(start < end){
            // Update the maximum heights seen so far
            left = Math.max(left, height[start]);
            right = Math.max(right, height[end]);

            // If the left boundary is smaller, it dictates the water level for the 'start' pointer
            if(left < right){
                max += left - height[start];
                start++;
            } 
            // Otherwise, the right boundary dictates the water level for the 'end' pointer
            else {
                max += right - height[end];
                end--;
            }
        }

        return max;
    }
}
