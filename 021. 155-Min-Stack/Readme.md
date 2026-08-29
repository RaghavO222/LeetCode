# 155. Min Stack

**Difficulty:** Medium

## Problem Statement
Design a stack that supports `push`, `pop`, `top`, and retrieving the minimum element in constant time.

Implement the `MinStack` class:
* `MinStack()` initializes the stack object.
* `void push(int val)` pushes the element `val` onto the stack.
* `void pop()` removes the element on the top of the stack.
* `int top()` gets the top element of the stack.
* `int getMin()` retrieves the minimum element in the stack.

You must implement a solution with $O(1)$ time complexity for each function.

## Intuition & Approach
A standard stack easily achieves $O(1)$ time for `push`, `pop`, and `top`. The challenge is retrieving the minimum value in $O(1)$ time without having to scan through the entire stack (which would take $O(N)$ time). 

To solve this, we can trade a little bit of space for a massive gain in speed. The core realization is that **stacks are Last-In-First-Out (LIFO)**. This means the minimum value at any specific "depth" of the stack will never change as long as those elements remain in the stack. 

1. **Store a Pair:** Instead of just pushing the integer value into the stack, we push an array of two integers: `[current_value, minimum_so_far]`.
2. **Pushing:** 
    * If the stack is empty, the newly pushed value is obviously the minimum. We push `[value, value]`.
    * If the stack is not empty, we look at the element currently at the top of the stack to see what the previous minimum was. Our new minimum is simply the smaller of our *new value* and the *previous minimum*. We push `[value, Math.min(value, previous_minimum)]`.
3. **Popping:** Because the "minimum so far" is bound directly to the element itself, when we pop the top element, we automatically revert to the correct previous minimum underneath it.
4. **Retrieving:** 
    * `top()` simply returns the 0th index of the top array.
    * `getMin()` simply returns the 1st index of the top array.

## Complexity Analysis

* **Time Complexity:** $O(1)$ for all operations (`push`, `pop`, `top`, `getMin`). We only ever interact with the very top of the stack, which is a constant time operation.
* **Space Complexity:** $O(N)$
  Where $N$ is the number of elements in the stack. We are storing an extra integer (the current minimum) for every single element we push onto the stack.

## Java Solution

```java
class MinStack {

    // Stack stores integer arrays: [value, current_minimum]
    private Stack<int[]> st;

    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int value) {
        if(st.isEmpty()){
            // If empty, the value is the minimum
            st.push(new int[]{value, value});
        } else {
            // Find the smaller of the new value and the previous minimum
            int currMin = st.peek()[1];
            st.push(new int[]{value, Math.min(value, currMin)});
        }
    }
    
    public void pop() {
        st.pop();
    }
    
    public int top() {
        // Return the actual value
        return st.peek()[0];
    }
    
    public int getMin() {
        // Return the minimum associated with the top element
        return st.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
