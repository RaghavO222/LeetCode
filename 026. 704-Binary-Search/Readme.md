# 704. Binary Search

**Difficulty:** Easy

## Problem Statement
Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

You must write an algorithm with $O(\log N)$ runtime complexity.

## Intuition & Approach
Because the input array is already sorted, we don't need to check every single element (which would take $O(N)$ time). Instead, we can use a **Divide and Conquer** strategy to eliminate half of the remaining search space with every step.

1. **Initialize Boundaries:** Set two pointers, `low` at the beginning of the array (index 0) and `high` at the end of the array (index `nums.length - 1`).
2. **Find the Middle:** While the `low` pointer is less than or equal to the `high` pointer, calculate the middle index. 
    * *Pro-Tip:* We use `low + (high - low) / 2` instead of `(low + high) / 2` to prevent integer overflow in Java if the array is massive.
3. **Compare and Narrow:**
    * If `nums[mid] == target`, we found the number! Return the `mid` index.
    * If `nums[mid] < target`, the target must be to the right of the middle. We discard the left half by moving `low` to `mid + 1`.
    * If `nums[mid] > target`, the target must be to the left of the middle. We discard the right half by moving `high` to `mid - 1`.
4. **Completion:** If the `while` loop finishes and we haven't returned an index, it means the `target` does not exist in the array. Return `-1`.

## Complexity Analysis

* **Time Complexity:** $O(\log N)$
  Where $N$ is the number of elements in the array. In each step, the search space is divided by two. This logarithmic behavior makes binary search incredibly fast, even for arrays with millions of elements.
* **Space Complexity:** $O(1)$
  We only allocate memory for three integer variables (`low`, `high`, and `mid`). The iterative approach avoids the call stack overhead of recursion, resulting in strictly constant space.

## Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while(low <= high){
            // Calculate mid to avoid integer overflow
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                return mid; // Target found
            }
            else if(nums[mid] < target){
                // Target is in the right half, adjust lower bound
                low = mid + 1;
            }
            else{
                // Target is in the left half, adjust upper bound
                high = mid - 1;
            }
        }

        // Target not found in the array
        return -1;
    }
}
