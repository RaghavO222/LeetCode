# 74. Search a 2D Matrix

**Difficulty:** Medium

## Problem Statement
You are given an `m x n` integer matrix `matrix` with the following two properties:
1. Each row is sorted in non-decreasing order.
2. The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in `matrix` or `false` otherwise.

You must write a solution in $O(\log(m \times n))$ time complexity.

## Intuition & Approach
Because every row is sorted and strictly greater than the row before it, if you were to lay all the rows end-to-end, they would form a single, perfectly sorted 1D array. 

Since binary search requires a sorted 1D space, we can run a standard binary search. However, actually flattening the array into a new 1D array would cost $O(m \times n)$ time and space. Instead, we can **simulate** a 1D array using math, mapping a 1D index back to 2D coordinates on the fly.

1. **Virtual 1D Array Boundaries:** We set `low` to `0` and `high` to `m * n - 1` (the total number of elements minus one).
2. **Calculate Midpoint:** We find our `mid` index just like a normal binary search: `low + (high - low) / 2`.
3. **Coordinate Mapping (The Magic Step):** To find the actual matrix value for this virtual 1D `mid` index, we use division and modulo operations based on the number of columns (`n`):
    * **Row Index:** `mid / n` (How many full rows fit into `mid`?)
    * **Column Index:** `mid % n` (What's the remainder/offset in the current row?)
4. **Standard Binary Search:** Once we have our mapped `val`, we compare it to the `target`:
    * If `val == target`, return `true`.
    * If `val > target`, the target is smaller, so search the left half (`high = mid - 1`).
    * If `val < target`, the target is larger, so search the right half (`low = mid + 1`).
5. **Completion:** If the loop ends without finding the target, return `false`.

## Complexity Analysis

* **Time Complexity:** $O(\log(m \times n))$
  Where $m$ is the number of rows and $n$ is the number of columns. We are effectively performing a standard binary search over the total number of elements, halving the search space at each step.
* **Space Complexity:** $O(1)$
  We only use a few integer variables (`m`, `n`, `low`, `high`, `mid`, `val`) to perform the coordinate math. No actual flattening happens in memory, keeping space strictly constant.

## Java Solution

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Set boundaries for the virtual 1D array
        int low = 0;
        int high = m * n - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            
            // Map the 1D 'mid' index back to 2D matrix coordinates
            int val = matrix[mid / n][mid % n];

            if(val == target){
                return true;
            } else if(val > target){
                // Target is in the left half
                high = mid - 1;
            } else{
                // Target is in the right half
                low = mid + 1;
            }
        }

        return false;
    }
}
