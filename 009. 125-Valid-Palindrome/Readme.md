# 125. Valid Palindrome

**Difficulty:** Easy

## Problem Statement
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

## Intuition & Approach
A naive approach would involve building a new string by filtering out all non-alphanumeric characters and reversing it, which takes $O(N)$ extra space. To optimize for space, this solution uses a **Two-Pointer technique** directly on the original string.

1. **Initialize Pointers:** Set up two pointers, `left` at the start of the string (index 0) and `right` at the end of the string (index `s.length() - 1`).
2. **Skip Non-Alphanumerics:** In a `while` loop, move the `left` pointer forward and the `right` pointer backward until they both land on valid alphanumeric characters. We use Java's built-in `Character.isLetterOrDigit()` for this.
3. **Compare Characters:** Once both pointers are on valid characters, convert them to lowercase and compare. 
    * If they don't match, the string is not a palindrome (return `false`).
    * If they match, move both pointers inward (`left++`, `right--`) and continue.
4. **Completion:** If the pointers cross (`left >= right`) without finding any mismatches, the string is a valid palindrome (return `true`).

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the string. We traverse the string with the two pointers at most once. The `while` loops inside simply advance the pointers and do not reset them, meaning each character is visited exactly once.
* **Space Complexity:** $O(1)$
  We only use two integer variables (`left` and `right`) for the pointers. We do not allocate any new strings or arrays, keeping memory usage constant regardless of the input size.

## Java Solution

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while(left < right){

            // Move left pointer to the next valid alphanumeric character
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }

            // Move right pointer to the previous valid alphanumeric character
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }

            // Compare the characters (case-insensitive)
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }

            // Move both pointers inward for the next comparison
            left++;
            right--;
        }

        return true;
    }
}
