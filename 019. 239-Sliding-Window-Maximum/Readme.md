# 239. Sliding Window Maximum

**Difficulty:** Hard

## Problem Statement
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

## Intuition & Approach
A brute-force approach would scan the `k` elements for every single window, leading to an $O(N \times k)$ time complexity. To achieve $O(N)$ time, we need a way to find the maximum in $O(1)$ time as the window slides. We can do this using a **Monotonic Decreasing Deque** (Double-Ended Queue).

The core idea: If a new number enters the window and it is larger than the numbers currently in our queue, those smaller numbers are useless. They can *never* become the maximum because the new, larger number will stay in the window longer than they will. 

1. **Store Indices, Not Values:** We store the *indices* of the array elements in the Deque, not the values themselves. This allows us to easily check if an element has "fallen out" of the left side of our sliding window.
2. **Process the First Window:** Iterate through the first `k` elements. Maintain the Deque in strictly decreasing order. If the current number `nums[i]` is greater than or equal to the number at the back of the Deque, pop the back element. Then, push the current index `i`.
3. **Record First Maximum:** The element at the front of the Deque (`peekFirst()`) will always be the largest in the current window.
4. **Slide the Window:** Iterate from `k` to the end of the array:
    * **Remove Out-of-Bounds:** If the index at the front of the Deque is less than or equal to `i - k`, it is no longer inside the current window. Remove it (`pollFirst()`).
    * **Maintain Monotonic Property:** Just like step 2, remove any elements from the back of the Deque that are smaller than or equal to the current number `nums[i]`.
    * **Add Current:** Push the current index `i` to the back.
    * **Record Maximum:** The front of the Deque again holds the index of the maximum value for this new window.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the array. Even though there is a `while` loop inside the `for` loop, every element is pushed into the Deque exactly once and popped from the Deque at most once. Therefore, the amortized cost per element is $O(1)$, resulting in a linear total runtime.
* **Space Complexity:** $O(k)$
  The Deque will store at most `k` indices at any given time (when the elements are strictly decreasing). The array used to store the results is $O(N - k + 1)$, which is the standard space required for the output, but auxiliary space is just $O(k)$.

## Java Solution

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        
        // Deque stores indices, keeping the values they point to in decreasing order
        Deque<Integer> dq = new ArrayDeque<>();

        // Process the first window of size 'k'
        for(int i = 0; i < k; i++){
            // Remove smaller elements from the back
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }

        // The maximum for the first window is at the front of the deque
        res[0] = nums[dq.peekFirst()];

        // Process the rest of the array, sliding the window
        for(int i = k; i < n; i++){
            
            // Remove the element at the front if it's no longer in the window
            if(dq.peekFirst() <= i - k){
                dq.pollFirst();
            }

            // Remove smaller elements from the back to maintain monotonic decreasing order
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i] ){
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(i);

            // Record the maximum for the current window
            res[i - k + 1] = nums[dq.peekFirst()];
        }

        return res;
    }
}
