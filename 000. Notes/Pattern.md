# 🧠 DSA Patterns Master Guide

This guide summarizes the core algorithmic patterns identified across the problems in this repository. Recognizing these patterns is the key to solving new, unseen problems without memorizing individual solutions.

---

## Pattern 1: Basic Hashing (Maps & Sets)
**The Core Concept:** Hashing is the ultimate **Time-Space Tradeoff**. By sacrificing a little bit of extra memory to store data we've already processed, we can drop our time complexity from a sluggish O(N²) (nested loops) down to a highly efficient O(N). We get O(1) instant lookups instead of scanning arrays repeatedly.

### 🔍 Clues to Look For
My first instinct should be a Hash Map or Hash Set if the problem asks me to:
1. Check if I have **already seen** an element.
2. Count the **frequencies** of elements or characters.
3. Look **backwards** to find a mathematical pair or complement.
4. **Group** a list of items based on a shared characteristic.

### 📝 Problems Solved
* **217. Contains Duplicate (Easy):** Dumped elements into a `HashSet`. If `set.contains()` returns true, a duplicate exists. Provides O(1) lookups.
* **242. Valid Anagram (Easy):** Used an `int[26]` array as a fixed-size hash map to count character frequencies. An array avoids autoboxing overhead and is much faster than a standard `HashMap` for a small, fixed alphabet.
* **1. Two Sum (Easy):** Stored `(value : index)` in a `HashMap`. For each number, calculated its complement (`target - current_number`) and checked if the map already had it, eliminating the need for a nested loop.
* **49. Group Anagrams (Medium):** Created a "signature" for strings by sorting their characters. Used this sorted string as the **Key** in a `HashMap` to group all original matching strings into a `List`.

---

## Pattern 2: Advanced Hashing (State Tracking & Lookups)
**The Core Concept:** Hash structures can be pushed beyond simple counting. They can track complex grid states or allow you to mathematically jump around an array to find sequences in strictly linear time.

### 🔍 Clues to Look For
1. You need to validate a grid or matrix based on multiple overlapping rules (rows, columns, sub-boxes).
2. You are asked to find a "sequence" or "longest consecutive" chain but are strictly limited to **O(N) time**, meaning `Arrays.sort()` is forbidden.

### 📝 Problems Solved
* **36. Valid Sudoku (Medium):** Used fixed-size `boolean[][]` arrays to track uniqueness across rows, columns, and 3x3 grids. Mapping characters to array indices acts as an incredibly fast, lightweight hash map with O(1) space and time complexity.
* **128. Longest Consecutive Sequence (Medium):** Dumped all numbers into a `HashSet`. By checking `!set.contains(n - 1)`, I could instantly identify the absolute starting points of sequences, ensuring every number was evaluated at most twice to maintain strict O(N) time.

---

## Pattern 3: Top 'K' Elements (Heaps / Priority Queues)
**The Core Concept:** Whenever a problem requires finding a specific subset of "best", "worst", or "most frequent" items, fully sorting the array takes O(N log N) time. Using a Priority Queue (Min-Heap or Max-Heap) optimizes this process by only maintaining the exact elements we care about.

### 🔍 Clues to Look For
1. The problem explicitly asks for the `k` most frequent, `k` largest, or `k` closest elements.
2. You need to continuously find the minimum or maximum value in a dynamically changing dataset.

### 📝 Problems Solved
* **347. Top K Frequent Elements (Medium):** Used a `HashMap` to count frequencies, then fed the keys into a **Min-Heap** sized strictly to `k`. By constantly popping the top (smallest) element when the heap exceeded `k`, the heap naturally retained the top `k` most frequent elements in O(N log K) time.

---

## Pattern 4: Prefix & Suffix Arrays
**The Core Concept:** This pattern is used when you need to calculate a result for a specific index based on all the elements *before* it and all the elements *after* it, precomputing running totals instead of using nested loops.

### 🔍 Clues to Look For
1. "Calculate the sum/product of an array **except self**."
2. Constraints restrict the use of division.
3. You need to quickly answer multiple queries about the sum or product of a specific "range".

### 📝 Problems Solved
* **238. Product of Array Except Self (Medium):** The product for any `index[i]` is simply `(Product of Left Elements) * (Product of Right Elements)`. Solved in O(N) time with one forward pass to calculate running left products, and one backward pass to multiply the running right products.

---

## Pattern 5: Two Pointers
**The Core Concept:** By using two indices (pointers) to traverse a data structure concurrently—usually starting from opposite ends and moving inward—we can evaluate pairs of data simultaneously. This often drops time complexity from O(N²) down to O(N) and keeps space complexity strictly at O(1).

### 🔍 Clues to Look For
1. The array is **already sorted** (a massive hint for Two Pointers).
2. You need to find a pair or triplet of elements that satisfy a condition (like a target sum).
3. You are testing for symmetry (like palindromes).
4. You need to calculate bounding areas or trapped capacities between two lines.

### 📝 Problems Solved
* **125. Valid Palindrome (Easy):** 
  * *The Clue:* Check if a sequence reads the same forward and backward.
  * *How it Solved It:* Placed pointers at the start and end. Skipped non-alphanumeric characters and moved inward, comparing characters case-insensitively. Avoided using O(N) extra space to build a reversed string.
* **167. Two Sum II - Input Array Is Sorted (Medium):** 
  * *The Clue:* Find a target sum in an already *sorted* array using strictly O(1) space.
  * *How it Solved It:* Placed pointers at opposite ends. If the sum was too small, moving the left pointer right increased it. If too large, moving the right pointer left decreased it. Exploited the sorted gradient perfectly.
* **15. 3Sum (Medium):** 
  * *The Clue:* Find three numbers that sum to zero, avoiding duplicates, without O(N³) nested loops.
  * *How it Solved It:* Sorted the array first. Fixed one number with a loop, then applied the "Two Sum II" inward pointer logic on the remaining right side. Allowed for easy duplicate skipping by checking adjacent values.
* **11. Container With Most Water (Medium):** 
  * *The Clue:* Maximize area (width × height) where the height is bottlenecked by the shorter line.
  * *How it Solved It:* Started with maximum width (pointers at ends). Calculated the area, then greedily moved the pointer of the *shorter* line inward to search for a taller line that might compensate for the lost width.
* **42. Trapping Rain Water (Hard):** 
  * *The Clue:* The water trapped at any index is determined by the maximum heights to its left and right.
  * *How it Solved It:* Tracked `left_max` and `right_max` from opposite ends. Because the smaller maximum determines the absolute water level, it calculated the trapped water at the side with the smaller max, then moved that pointer inward.
