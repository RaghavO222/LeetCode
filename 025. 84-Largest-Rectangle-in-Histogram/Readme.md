# 84. Largest Rectangle in Histogram

**Difficulty:** Hard

## Problem Statement
Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return the area of the largest rectangle in the histogram.

## Intuition & Approach
The brute-force way to solve this would be to expand out from every single bar to the left and right, checking how wide a rectangle we can make if we use that bar's height as our limit. This would take $O(N^2)$ time. We can optimize this to $O(N)$ using a **Monotonic Increasing Stack**.

For any given bar, the maximum width of a rectangle that can be formed using *that bar's height* is bounded by the **Next Smaller Element** on the left and the **Next Smaller Element** on the right.

1. **Find Left Boundaries:** We iterate from left to right. We maintain a stack of indices, keeping the heights in strictly increasing order. If we encounter a bar smaller than or equal to the top of the stack, we pop the stack. 
    * The element left at the top of the stack is the index of the nearest smaller bar to the left.
    * If the stack is empty, it means there is no smaller bar, so the boundary extends all the way to index `-1`. We store these boundaries in a `left` array.
2. **Find Right Boundaries:** We clear the stack and do the exact same process, but this time iterating from right to left.
    * If the stack becomes empty, the boundary extends all the way to the end of the array, index `n`. We store these boundaries in a `right` array.
3. **Calculate Maximum Area:** Now that we know exactly how far left and right each bar can expand, we loop through the array one last time. 
    * The total width for the $i^{th}$ bar is `right[i] - left[i] - 1`.
    * The area is `heights[i] * width`.
    * We track and return the maximum area found.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of bars in the histogram. We pass through the array three times (once for left boundaries, once for right boundaries, and once to calculate the area). Inside the loops, every index is pushed to and popped from the stack at most once, making the operations strictly linear.
* **Space Complexity:** $O(N)$
  We allocate memory for two integer arrays (`left` and `right`) of size $N$, and a stack that can potentially hold up to $N$ elements in the worst case (a strictly increasing histogram).

## Java Solution

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        // Arrays to store the nearest smaller bar's index on the left and right
        int[] left = new int[n];
        int[] right = new int[n];
        
        // Stack stores indices of the histogram bars
        Stack<Integer> st = new Stack<>();

        // 1. Find the Next Smaller Element to the left
        for(int i = 0; i < n; i++){
            // Maintain monotonic increasing order
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            // If stack is empty, boundary is -1. Otherwise, it's the top element.
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        // Clear the stack to reuse it for the right side
        st.clear();

        // 2. Find the Next Smaller Element to the right
        for(int i = n - 1; i >= 0; i--){
            // Maintain monotonic increasing order
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            // If stack is empty, boundary is n (out of bounds on the right)
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        // 3. Calculate max area for each bar
        int res = 0;
        for(int i = 0; i < n; i++){
            // Width is the distance between the right and left smaller bars
            int width = right[i] - left[i] - 1;
            res = Math.max(res, heights[i] * width);
        }
        
        return res;
    }
}