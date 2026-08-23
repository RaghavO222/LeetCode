# LeetCode 238: Product of Array Except Self

## 📝 Problem Statement
Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`. 
* **Constraint:** You must write an algorithm that runs in `$O(N)$` time and without using the division operation.

**Example:**
- **Input:** `nums = [1, 2, 3, 4]`
- **Output:** `[24, 12, 8, 6]`

---

## 💡 The Intuition: Left Product × Right Product
Since we cannot use division, we have to find another way to exclude the current number. 

If you look closely at any number in the array, the product of everything *except* that number is simply:
**(The product of all numbers to its LEFT) × (The product of all numbers to its RIGHT)**

For example, in `[1, 2, 3, 4]`, to find the answer for `3` (index 2):
* Left product: `1 * 2 = 2`
* Right product: `4`
* Answer: `2 * 4 = 8`

### Approach 1: The Stepping Stone (Using Extra Space)
The most intuitive way to solve this is to create two extra arrays:
1. `pre[]`: Stores the running product of elements from left to right.
2. `suff[]`: Stores the running product of elements from right to left.
3. Finally, loop through the array and set `ans[i] = pre[i] * suff[i]`.

*This works perfectly in `$O(N)$` time, but it uses `$O(N)$` extra space. We can do better!*

### Approach 2: The Optimized Solution (O(1) Extra Space)
Instead of creating separate `pre` and `suff` arrays, we can calculate these running products on the fly and store them directly in our final `ans` array.

1. **Forward Pass (Left to Right):** We use a variable `curr` (starting at 1) to keep track of the running left product. We place this into `ans[i]`, and then update `curr` by multiplying it by `nums[i]`.
2. **Backward Pass (Right to Left):** We reset `curr` to 1. We loop backward, multiplying the existing value in `ans[i]` (which currently holds the left product) by `curr` (which now represents the running right product). 

### ⏱️ Complexity Analysis
Time Complexity: $O(N)$

We loop through the array exactly two times (once forward, once backward). $O(2N)$ simplifies to $O(N)$.

Space Complexity: $O(1)$

The problem explicitly states that the output array ans does not count towards extra space for space complexity analysis. We only use a single integer variable (curr), making the auxiliary space $O(1)$.

---

## 💻 Java Implementation

```java
import java.util.Arrays;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // Initialize the array with 1s
        Arrays.fill(ans, 1);
        
        // PASS 1: Calculate left products
        int curr = 1;
        for (int i = 0; i < n; i++) {
            ans[i] *= curr;        // Store the product of everything to the left
            curr *= nums[i];       // Update the running product for the next index
        }
        
        // PASS 2: Calculate right products and multiply with left products
        curr = 1; // Reset running product for the backward pass
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;        // Multiply the left product by everything to the right
            curr *= nums[i];       // Update the running product for the next index
        }
        
        return ans;
    }
}
