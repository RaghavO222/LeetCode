# 3. Longest Substring Without Repeating Characters

**Difficulty:** Medium

## Problem Statement
Given a string `s`, find the length of the longest substring without repeating characters.

## Intuition & Approach
A brute-force approach would check every possible substring to see if it has duplicate characters, taking $O(N^3)$ time. We can optimize this to a single pass using the **Sliding Window technique** combined with a `HashSet`.

Think of the "window" as the current valid substring bounded by a `left` and `right` pointer.

1. **Initialize the Window:** We use a `left` pointer starting at index `0` and a `right` pointer that iterates through the string. We also initialize a `HashSet` to keep track of the unique characters currently inside our window.
2. **Expand the Window:** As the `right` pointer moves forward, we examine the character it points to. 
3. **Handle Duplicates (Shrink the Window):** If the character at `right` is *already* in our `HashSet`, we have a repeating character. Our window is no longer valid. To fix this, we shrink the window from the left. We repeatedly remove the character at the `left` pointer from the `HashSet` and move `left` forward until the duplicate character is entirely out of our window.
4. **Update and Record:** Once the window is valid again (no duplicates), we add the new `right` character to the `HashSet`. We then calculate the current window's size (`right - left + 1`) and update our `maxL` if this new window is the largest we've seen so far.
5. **Completion:** By the time the `right` pointer reaches the end of the string, `maxL` will hold the length of the longest valid substring.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the string. In the worst-case scenario (e.g., `"abccba"`), each character is visited at most twice—once by the `right` pointer and once by the `left` pointer. Therefore, the time complexity is strictly linear.
* **Space Complexity:** $O(K)$
  Where $K$ is the size of the character set (e.g., 26 for English letters, or up to 128/256 for standard ASCII). In the worst case, the `HashSet` will store all unique characters of the string. Because the character set is typically fixed and small, this is often considered $O(1)$ in practice.

## Java Solution

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxL = 0;
        HashSet<Character> charSet = new HashSet<>();

        // The 'right' pointer expands the window
        for(int right = 0; right < s.length(); right++){
            
            // If a duplicate is found, shrink the window from the left
            while(charSet.contains(s.charAt(right))){
                charSet.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the set
            charSet.add(s.charAt(right));
            
            // Update the maximum length found so far
            maxL = Math.max(maxL, right - left + 1);
        }
        
        return maxL;        
    }
}
