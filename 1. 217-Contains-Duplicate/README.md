## 217. Contains Duplicate

**Difficulty:** Easy
**Language:** Java

### Approach & What I Learned
- **HashSet Properties:** A `HashSet` stores only unique elements. It automatically handles duplicates.
- **Fast Lookups:** The `.contains()` method in a `HashSet` takes **O(1)** (constant time) on average. This is because it uses hashing to find elements instantly instead of iterating through a list.
- By checking if the number exists in the set *before* adding it, we can immediately exit the loop as soon as a duplicate is found.

### Complexity
- **Time Complexity:** O(N) — We iterate through the array of length N exactly once. The HashSet lookup and insertion both take O(1) time.
- **Space Complexity:** O(N) — In the worst-case scenario (an array with all unique elements), the HashSet will grow to store all N elements.
