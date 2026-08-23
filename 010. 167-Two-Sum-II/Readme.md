# 167. Two Sum II - Input Array Is Sorted

**Difficulty:** Medium

## Problem Statement
Given a **1-indexed** array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific `target` number. 

Return the indices of the two numbers, `index1` and `index2`, added by one as an integer array `[index1, index2]` of length 2. The solution must use only constant extra space.

## Intuition & Approach
If the array was not sorted, we would typically use a `HashMap` to find the complement of each number. However, since the array is already sorted, we can take advantage of its ascending order using a **Two-Pointer technique**.

1. **Initialize Pointers:** Place a `left` pointer at the very beginning of the array (smallest value) and a `right` pointer at the very end (largest value).
2. **Calculate and Compare:** In a `while` loop, calculate the sum of the elements at the `left` and `right` pointers.
    * **Match Found:** If `numbers[left] + numbers[right] == target`, we have found our answer. Since the problem requires 1-based indexing, we return `[left + 1, right + 1]`.
    * **Sum is Too Small:** If the sum is `< target`, the current numbers are not large enough. Since the array is sorted, we can only increase our sum by moving the `left` pointer to the right (`left++`).
    * **Sum is Too Large:** If the sum is `> target`, we overshot. We need a smaller number, so we decrease our sum by moving the `right` pointer to the left (`right--`).
3. **Completion:** The loop continues narrowing the search window until the exact target is met (the problem guarantees exactly one solution exists).

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of elements in the array. The `left` pointer only moves right, and the `right` pointer only moves left. They will meet in the middle at worst, meaning we scan through the array exactly once.
* **Space Complexity:** $O(1)$
  We only use two integer variables (`left` and `right`) to keep track of indices. We do not use any additional data structures, so memory usage remains constant regardless of the array's size.

## Java Solution

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while(left < right){
            // Target found
            if(numbers[left] + numbers[right] == target){
                return new int[]{left + 1, right + 1};
            }

            // Sum is too small, move left pointer to increase sum
            if(numbers[left] + numbers[right] < target){
                left++;
            }

            // Sum is too large, move right pointer to decrease sum
            if(numbers[left] + numbers[right] > target){
                right--;
            }
        }   

        // Fallback (Problem guarantees a valid answer exists)
        return new int[]{};
    }
}
