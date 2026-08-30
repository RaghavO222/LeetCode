# 739. Daily Temperatures

**Difficulty:** Medium

## Problem Statement
Given an array of integers `temperatures` represents the daily temperatures, return an array `answer` such that `answer[i]` is the number of days you have to wait after the $i^{th}$ day to get a warmer temperature. 

If there is no future day for which this is possible, keep `answer[i] == 0` instead.

## Intuition & Approach
A brute-force approach would require looking ahead using a nested loop for every single day, leading to a slow $O(N^2)$ time complexity. We can optimize this to a single pass using a **Monotonic Decreasing Stack**.

The core idea is to use a stack to remember the days (specifically, their indices) for which we *haven't found a warmer day yet*. 

1. **Store Indices:** We use a stack to store the **indices** of the temperatures, not the temperatures themselves. This allows us to easily calculate the distance (number of days) between two temperatures.
2. **Iterate:** We loop through the array of temperatures day by day.
3. **Resolve Waiting Days:** For each day, we check if the current temperature is warmer than the temperature of the day currently at the top of the stack.
    * If it *is* warmer, it means we finally found a warmer day for that past date! We pop the old index from the stack, calculate the difference between the current index (`i`) and the popped index, and store that distance in our `res` array.
    * We use a `while` loop to repeat this process. A single really hot day might resolve several cooler days that were waiting in the stack.
4. **Push the Current Day:** Once we've resolved all the previous days we could, we push the current day's index onto the stack so it can wait for its own warmer day in the future.
5. **Default Zeros:** In Java, integer arrays are initialized with `0` by default. Any indices left in the stack at the end of the loop never found a warmer day, and their corresponding spots in the `res` array correctly remain `0`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of elements in the `temp` array. Although there is a `while` loop nested inside the `for` loop, every index is pushed onto the stack exactly once and popped from the stack at most once. Therefore, the total number of operations scales linearly.
* **Space Complexity:** $O(N)$
  In the worst-case scenario (e.g., temperatures are continuously decreasing like `[100, 90, 80, 70]`), we will never find a warmer day, meaning every single index will be pushed onto the stack, requiring linear extra space.

## Java Solution

```java
class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int[] res = new int[temp.length];
        
        // Stack stores the INDICES of days waiting for a warmer temperature
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < temp.length; i++){
            // While the current day is warmer than the day at the top of the stack
            while(!st.isEmpty() && temp[st.peek()] < temp[i]){
                // We found a warmer day! Calculate the distance in days.
                res[st.peek()] = i - st.pop();
            }
            
            // Push the current day's index onto the stack to wait for a warmer day
            st.push(i);
        }

        return res;
    }
}
