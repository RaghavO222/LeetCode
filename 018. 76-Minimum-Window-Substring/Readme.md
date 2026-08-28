# 76. Minimum Window Substring

**Difficulty:** Hard

## Problem Statement
Given two strings `s` and `t` of lengths `m` and `n` respectively, return the **minimum window substring** of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

## Intuition & Approach
This problem requires a **Variable-Size Sliding Window**. We need to expand a window until it contains all the required characters from `t`, and then shrink it from the left to make it as small as possible while still remaining valid.

Instead of using `HashMaps` (which can add overhead), this solution uses two integer arrays of size 256 to map standard ASCII characters to their frequencies.

1. **Target Frequencies:** First, populate `mapT` with the frequency of every character in the target string `t`.
2. **Expand the Window:** Use a `right` pointer to iterate through string `s`. As you visit each character, increment its frequency in `mapS`.
3. **Validate the Window:** After adding a character, use a helper method `contain(mapS, mapT)` to check if our current window has *at least* the required number of every character present in `t`.
4. **Shrink and Record:** If the window is valid (`contain` returns `true`), it's time to optimize! 
    * First, check if this valid window is smaller than our previously recorded `minLen`. If so, update `minLen` and store the starting index (`minStart`).
    * Next, try to shrink the window from the left. Decrement the frequency of the character at the `left` pointer in `mapS` and move `left` forward. 
    * The `while` loop continues to shrink the window and record new minimums until the window finally becomes invalid.
5. **Completion:** Once the `right` pointer finishes scanning `s`, extract the substring using `minStart` and `minLen`. If `minLen` was never updated, return an empty string.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of string `s`. The `right` and `left` pointers each traverse the string at most once. The `contain` method iterates 256 times, which is a constant $O(1)$ operation. Thus, the overall time complexity remains strictly linear. *(Note: While $O(N)$ is true, scanning the 256-size array repeatedly adds a constant factor overhead. More optimized approaches use a `matched_characters` integer counter to skip the 256-loop).*
* **Space Complexity:** $O(1)$
  We allocate two integer arrays of exactly size 256. Because this size never changes regardless of the input strings' lengths, the space complexity is constant.

## Java Solution

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        // Populate required frequencies from string t
        for(char ch: t.toCharArray()){
            mapT[ch]++;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        // 'right' pointer expands the window
        for(int right = 0; right < s.length(); right++){
            mapS[s.charAt(right)]++;

            // While the window contains all characters of t, try to shrink it
            while(contain(mapS, mapT)){
                
                // Record the new minimum window
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                // Shrink from the left
                mapS[s.charAt(left)]--;
                left++;
            }
        }

        // If minLen wasn't changed, no valid window was found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // Helper method to check if mapS contains all frequencies required by mapT
    public boolean contain(int[] mapS, int[] mapT){
        for(int i = 0; i < 256; i++){
            if(mapT[i] > mapS[i]){
                return false;
            }
        }
        return true;
    }
}
