# 153. Find Minimum in Rotated Sorted Array

**Difficulty:** Medium

## Problem Statement
Suppose an array of length `n` sorted in ascending order is rotated between `1` and `n` times. 
Given the sorted rotated array `nums` of unique elements, return the minimum element of this array.

You must write an algorithm that runs in $O(\log N)$ time.

## Intuition & Approach
Because the array was originally sorted, it now consists of two sorted portions (e.g., `[4,5,6, 1,2,3]`). The minimum element is the "pivot" point where the rotation happened. We can find this in logarithmic time using Binary Search.

1. **Initialize:** Set `low` to the start and `high` to the end. Initialize `res` to an arbitrary element in the array to act as our tracking minimum.
2. **Binary Search:** Calculate `mid`. We immediately update `res` with the minimum of our current `res` and `nums[mid]`.
3. **Determine the Sorted Half:** We compare `nums[mid]` to `nums[high]`:
    * If `nums[mid] > nums[high]`, it means the `mid` element is part of the left (larger) sorted portion. The actual minimum *must* be to the right of `mid`. We discard the left half (`low = mid + 1`).
    * If `nums[mid] <= nums[high]`, the right half is sorted, meaning `nums[mid]` is the smallest element in that right half. The true minimum could be `mid` itself or something to its left. We discard the right half (`high = mid - 1`).
4. **Completion:** When the loop finishes, `res` will hold the absolute minimum value.

## Complexity Analysis

* **Time Complexity:** $O(\log N)$
  Where $N$ is the number of elements in the array. We halve the search space at each step.
* **Space Complexity:** $O(1)$
  We only allocate memory for standard pointers, making the space requirement constant.

## Java Solution

```java
class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        int res = nums[low + (high - low) / 2];

        while(low <= high){
            int mid = low + (high - low) / 2;
            
            // Update our minimum tracker
            res = Math.min(res, nums[mid]);

            // If mid is greater than the rightmost element, the min is to the right
            if(nums[mid] > nums[high]){
                low = mid + 1;
            } else {
                // Otherwise, the min is to the left (or is mid itself)
                high = mid - 1;
            }
        }

        return res;
    }
}
