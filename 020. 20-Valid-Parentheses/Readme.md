# 20. Valid Parentheses

**Difficulty:** Easy

## Problem Statement
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

## Intuition & Approach
This problem is all about order of operations. The most recently opened bracket must be the very first one to be closed. This "Last-In-First-Out" (LIFO) behavior makes a **Stack** the perfect data structure for the job.

1. **Iterate Through the String:** We examine each character one by one.
2. **Push Open Brackets:** If the character is an opening bracket (`(`, `[`, `{`), we push it onto our stack. It is now waiting to be closed.
3. **Handle Closing Brackets:** If the character is a closing bracket, we need to check two things:
    * **Is the stack empty?** If it is, it means we have a closing bracket without a corresponding opening bracket (e.g., `"]"`). This is invalid, so we return `false`.
    * **Does it match?** We pop the top element off the stack (the most recently opened bracket). If it doesn't match the current closing bracket (e.g., we popped a `(` but the current character is `]`), the order is mismatched, and we return `false`.
4. **Final Check:** After processing the entire string, the stack *must* be empty. If it's not empty, it means there are unclosed opening brackets left over (e.g., `"()("`), so we return `false`. If it is empty, we return `true`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the string `s`. We traverse the string exactly once. Pushing to and popping from a stack are $O(1)$ constant time operations.
* **Space Complexity:** $O(N)$
  In the worst-case scenario (a string composed entirely of opening brackets like `"((((("`), we will push every single character onto the stack, requiring space proportional to the length of the string.

## Java Solution

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            // Handle closing brackets
            else {
                // If stack is empty, there's no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Pop the last opened bracket and check for a mismatch
                char top = stack.pop();
                
                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }
        
        // If stack is empty, all brackets were properly matched and closed
        return stack.isEmpty();
    }
}
