# 15. 3Sum

**Difficulty:** Medium

## Problem Statement
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must **not** contain duplicate triplets.

## Intuition & Approach
The naive approach (three nested loops) would take $O(N^3)$ time, which is too slow. To optimize this, we can sort the array and break the problem down into a series of **Two Sum II** problems.

1. **Sort the Array:** Sorting is the key to this algorithm. It allows us to efficiently use pointers and makes it easy to skip duplicate numbers so we don't produce duplicate triplets.
2. **Fix One Number:** We iterate through the array using a `for` loop (pointer `i`). For each number `nums[i]`, we treat it as the first element of our potential triplet. Our new target for the remaining two numbers becomes `-nums[i]` (since we want the total sum to be `0`).
3. **Two-Pointer Search:** For the remaining part of the array to the right of `i`, we use a `left` pointer (`j = i + 1`) and a `right` pointer (`k = nums.length - 1`). 
    * If the `sum > 0`, the total is too large, so we move the `right` pointer left (`k--`).
    * If the `sum < 0`, the total is too small, so we move the `left` pointer right (`j++`).
    * If the `sum == 0`, we found a valid triplet! We record it and move the `left` pointer forward.
4. **Skip Duplicates:** This is the most crucial part to prevent duplicate triplets:
    * If our fixed number `nums[i]` is the same as the previous number `nums[i - 1]`, we skip it.
    * After finding a successful triplet, if the new `nums[j]` is the same as the previous `nums[j - 1]`, we keep advancing `j` until we hit a new number.

## Complexity Analysis

* **Time Complexity:** $O(N^2)$
  Sorting the array takes $O(N \log N)$ time. After sorting, we iterate through the array once (taking $O(N)$ time), and for each element, we do a two-pointer scan taking $O(N)$ time. The overall time complexity is dominated by the nested loops, giving us $O(N^2)$.
* **Space Complexity:** $O(1)$ (or $O(\log N)$)
  We are only using a few pointers. However, depending on the sorting algorithm implementation in Java (Dual-Pivot Quicksort for primitives), it may take $O(\log N)$ auxiliary stack space. The space required for the output list is not typically counted towards space complexity.

## Java Solution

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            // Step 4a: Skip duplicates for our fixed pointer 'i'
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            // Step 2 & 3: Set up Two Pointers
            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > 0){
                    k--;
                } else if(sum < 0){
                    j++;
                } else {
                    // Match found! Add it to our list
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    // Step 4b: Skip duplicates for our left pointer 'j'
                    while(nums[j] == nums[j - 1] && j < k){
                        j++;
                    }
                }
            }
        }
        
        return list;
    }
}
