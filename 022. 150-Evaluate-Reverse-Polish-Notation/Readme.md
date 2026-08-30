# 150. Evaluate Reverse Polish Notation

**Difficulty:** Medium

## Problem Statement
You are given an array of strings `tokens` that represents an arithmetic expression in a Reverse Polish Notation (RPN).

Evaluate the expression. Return an integer that represents the value of the expression.

* Valid operators are `+`, `-`, `*`, and `/`.
* Each operand may be an integer or another expression.
* The division between two integers always truncates toward zero.
* There will not be any division by zero.

## Intuition & Approach
Reverse Polish Notation (postfix notation) is a mathematical notation in which every operator follows all of its operands (e.g., `3 4 +` instead of `3 + 4`). 

Because we need to apply operators to the most recently seen numbers, a **Stack** is the perfect data structure for this "Last-In-First-Out" requirement.

1. **Iterate through Tokens:** Process each string in the array one by one.
2. **Push Numbers:** If the token is a number, we push it onto the stack.
3. **Process Operators:** If the token is an operator (`+`, `-`, `*`, `/`), we pop the top two numbers from the stack to perform the math.
    * **Crucial Detail:** The first number popped is the **right** operand (`a`), and the second number popped is the **left** operand (`b`). This order is extremely important for non-commutative operations like subtraction (`b - a`) and division (`b / a`).
4. **Push Results:** After evaluating the small expression, we push the result back onto the stack so it can be used by future operators.
5. **Final Result:** Once the loop finishes processing all tokens, there will be exactly one number left on the stack. This is the final evaluated answer.

## Complexity Analysis

* **Time Complexity:** O(N)
  Where N is the number of elements in the `tokens` array. We iterate through the array exactly once. Pushing to and popping from the stack, as well as the String-to-Integer parsing, all take O(1) constant time.
* **Space Complexity:** O(N)
  In the worst-case scenario (an expression with many numbers up front and operators at the very end), the stack will store up to (N + 1) / 2 numbers. Thus, the space scales linearly with the input size.

## Java Solution

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();

        for(int i = 0; i < tokens.length; i++){
            if(tokens[i].equals("+")){
                int a = Integer.parseInt(st.pop());
                int b = Integer.parseInt(st.pop());
                int t = b + a;
                st.push(Integer.toString(t));

            } else if(tokens[i].equals("-")){
                int a = Integer.parseInt(st.pop());
                int b = Integer.parseInt(st.pop());
                int t = b - a;
                st.push(Integer.toString(t));

            } else if(tokens[i].equals("*")){
                int a = Integer.parseInt(st.pop());
                int b = Integer.parseInt(st.pop());
                int t = b * a;
                st.push(Integer.toString(t));

            } else if(tokens[i].equals("/")){
                int a = Integer.parseInt(st.pop());
                int b = Integer.parseInt(st.pop());
                int t = b / a;
                st.push(Integer.toString(t));

            } else{
                st.push(tokens[i]);
            }
        }

        return Integer.parseInt(st.pop());
    }
}
