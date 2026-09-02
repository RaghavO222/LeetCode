# 33. Search in Rotated Sorted Array

**Difficulty:** Medium

## Problem Statement
There is an integer array `nums` sorted in ascending order (with distinct values).
Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index. Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not in `nums`.

You must write an algorithm with $O(\log N)$ runtime complexity.

## Intuition & Approach
Like finding the minimum in a rotated array, we can use Binary Search. The trick here is that while the whole array isn't perfectly sorted, dividing it in half guarantees that **at least one of the two halves will always be perfectly sorted**.

1. **Calculate Midpoint:** Find `mid` and check if it's the `target`.
2. **Find the Sorted Half:**
    * **Left half is sorted (`nums[low] <= nums[mid]`):** We check if the `target` falls strictly within the range of this sorted left half (`nums[low] <= target < nums[mid]`). If it does, we discard the right side. If it doesn't, we discard the left side.
    * **Right half is sorted (the `else` block):** We check if the `target` falls strictly within the range of this sorted right half (`nums[mid] < target <= nums[high]`). If it does, we discard the left side. If it doesn't, we discard the right side.
3. **Completion:** If the loop ends, the target isn't in the array, so we return `-1`.

## Complexity Analysis

* **Time Complexity:** $O(\log N)$
  We discard exactly half of the search space on every iteration.
* **Space Complexity:** $O(1)$
  Only a few integer pointers are used.

## Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                return mid;
            }

            // Check if the left portion is sorted
            if(nums[low] <= nums[mid]){
                // Check if target is inside the sorted left portion
                if(nums[low] <= target && target < nums[mid]){
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Otherwise, the right portion must be sorted
            else {
                // Check if target is inside the sorted right portion
                if(nums[mid] < target && target <= nums[high]){
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }   
        return -1;
    }
}
