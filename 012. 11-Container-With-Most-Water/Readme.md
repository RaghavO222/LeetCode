# 11. Container With Most Water

**Difficulty:** Medium

## Problem Statement
You are given an integer array `height` where each element represents the height of a vertical line on a graph. Your goal is to find two lines that, along with the x-axis, form a container that holds the maximum amount of water. 

Return the maximum area of water the container can store.

## Intuition & Approach
A brute-force approach would check every possible pair of lines, resulting in a slow $O(N^2)$ time complexity. We can optimize this to a single pass using a **Two-Pointer technique**.

The volume of water a container can hold is determined by two factors:
1. **Width:** The horizontal distance between the two lines (`right - left`).
2. **Height:** The height of the *shorter* line (`Math.min(height[left], height[right])`), because water would simply spill over the shorter side.

**The Strategy:**
1. **Start Wide:** Initialize a `left` pointer at the beginning and a `right` pointer at the end of the array. This guarantees we start with the maximum possible width.
2. **Calculate Area:** At each step, calculate the current area and update the `max` area variable if the current one is larger.
3. **Move the Bottleneck (Greedy Choice):** As we move pointers inward, the width strictly decreases. To find a potentially larger area, we *must* find taller lines to compensate for the lost width. Therefore, we always move the pointer that points to the **shorter** line. Moving the taller line is pointless because the container's height is always capped by the shorter line anyway.
4. **Repeat** until the `left` and `right` pointers meet.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of elements in the `height` array. The pointers scan through the array from opposite ends and meet in the middle, evaluating each line exactly once.
* **Space Complexity:** $O(1)$
  We only allocate memory for three integer variables (`max`, `left`, `right`). No extra data structures are used, making the space requirement strictly constant.

## Java Solution

```java
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right){
            // Calculate current area and update max if needed
            max = Math.max(max, (right - left) * (Math.min(height[left], height[right])));

            // Move the pointer of the shorter line inward
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
