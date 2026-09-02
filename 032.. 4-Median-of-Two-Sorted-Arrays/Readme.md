# 4. Median of Two Sorted Arrays

**Difficulty:** Hard

## Problem Statement
Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

The overall run time complexity should be $O(\log(m+n))$.

## Intuition & Approach
This is one of the hardest binary search problems. Instead of merging the arrays (which takes $O(N+M)$ time), we can binary search for the **correct partition line** that divides the combined arrays into two equal halves.

1. **Optimize Search Space:** We always perform the binary search on the *smaller* of the two arrays to minimize the search space.
2. **Partitioning:** We partition the smaller array at index `partitionX` (our `mid` in the binary search). Because the total left half must equal the total right half, the partition index for the larger array `partitionY` is just `(totalLength + 1) / 2 - partitionX`.
3. **Cross-Check Boundaries:** We check the elements immediately left and right of the partition lines in both arrays (`l1`, `r1`, `l2`, `r2`).
    * **Valid Partition:** If `l1 <= r2` and `l2 <= r1`, we found the exact correct cut! We calculate the median based on whether the total length is even or odd.
    * **Cut Too Far Right:** If `l1 > r2`, it means our cut on the smaller array is too far right. We must move it left (`high = partitionX - 1`).
    * **Cut Too Far Left:** Otherwise, our cut is too far left. We move it right (`low = partitionX + 1`).

## Complexity Analysis

* **Time Complexity:** $O(\log(\min(N, M)))$
  Because we only binary search on the smaller array, the time complexity is strictly logarithmic with respect to the smaller length.
* **Space Complexity:** $O(1)$
  No auxiliary arrays are created; we just map logical partitions onto the existing arrays.

## Java Solution

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure we always binary search on the smaller array
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        
        int totalLength = nums1.length + nums2.length;
        int low = 0;
        int high = smaller.length;

        while(low <= high){
            int partitionX = (low + high) / 2;
            int partitionY = (totalLength + 1) / 2 - partitionX;

            // Handle edge cases where partition is at the extreme ends
            int l1 = partitionX == 0 ? Integer.MIN_VALUE : smaller[partitionX - 1];
            int r1 = partitionX == smaller.length ? Integer.MAX_VALUE : smaller[partitionX];

            int l2 = partitionY == 0 ? Integer.MIN_VALUE : larger[partitionY - 1];
            int r2 = partitionY == larger.length ? Integer.MAX_VALUE : larger[partitionY];

            // Valid partition found
            if(l1 <= r2 && l2 <= r1){
                if((totalLength) % 2 == 0){
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            }
            
            // Adjust partition on the smaller array
            if(l1 > r2){
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        
        return 0;        
    }
}
