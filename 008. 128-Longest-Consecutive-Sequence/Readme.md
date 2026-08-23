# 128. Longest Consecutive Sequence

**Difficulty:** Medium

## Problem Statement
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence. 

You must write an algorithm that runs in $O(N)$ time.

## Intuition & Approach
A naive approach would be to sort the array, which takes $O(N \log N)$ time. To achieve the required $O(N)$ time complexity, this solution uses a **HashSet**.

1. **Store Elements:** First, we insert all elements of the array into a `HashSet`. This naturally handles duplicates and gives us $O(1)$ constant time lookups.
2. **Find Sequence Starts:** We iterate through the set. For every number `n`, we check if `n - 1` exists in the set.
    * If `n - 1` **is present**, `n` is somewhere in the middle (or end) of a sequence. We ignore it.
    * If `n - 1` **is missing**, it means `n` is the *absolute starting point* of a new consecutive sequence.
3. **Count the Sequence:** Starting from `n`, we continuously check if the next consecutive numbers (`n + 1`, `n + 2`, etc.) exist in the set, incrementing our `length` counter as we go.
4. **Track Maximums:** We continually update our maximum length variable (`l`) with the highest sequence length found.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Although there is a `while` loop nested inside a `for` loop, the inner `while` loop only runs when we find the *start* of a sequence. Because of this check (`!nSet.contains(n - 1)`), each number in the array is evaluated at most twice. Thus, the time complexity remains strictly linear.
* **Space Complexity:** $O(N)$
  We allocate extra memory for the `HashSet`, which in the worst-case scenario (all unique elements) will store $N$ integers.

## Java Solution

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> nSet = new HashSet<>();

        for(int n: nums){
            nSet.add(n);
        }

        int l = 0;

        for(int n : nSet){
            // Only start counting if 'n' is the beginning of a sequence
            if(!nSet.contains(n - 1)){
                int length = 1;

                while(nSet.contains(n + length)){
                    length++;
                }

                l = Math.max(l, length);
            }
        }

        return l;
    }
}
