# 567. Permutation in String

**Difficulty:** Medium

## Problem Statement
Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.

In other words, return `true` if one of `s1`'s permutations is the substring of `s2`.

## Intuition & Approach
A permutation of a string means it contains the exact same characters with the exact same frequencies, just potentially in a different order. Therefore, to check if a substring of `s2` is a permutation of `s1`, we just need to compare their character frequencies.

Since the substring must be exactly the length of `s1`, we can use a **Fixed-Size Sliding Window**.

1. **Edge Case:** If `s1` is longer than `s2`, it's impossible for `s2` to contain a permutation of `s1`.
2. **Frequency Arrays:** Since the problem constraints usually specify lowercase English letters, we can use two integer arrays of size 26 (`count1` and `count2`) instead of `HashMaps`. This is much faster and uses strictly constant memory.
3. **First Window:** We populate `count1` with the character frequencies of `s1`. Simultaneously, we populate `count2` with the frequencies of the *first window* in `s2` (which is exactly `s1.length()` characters long). 
4. **Initial Check:** If `Arrays.equals(count1, count2)` is true, we immediately return `true`.
5. **Slide the Window:** We start a loop from `s1.length()` to the end of `s2`. For each step, we slide our window one character to the right:
    * Add the new character entering the right side of the window to `count2`.
    * Remove the old character that is falling off the left side of the window from `count2`.
    * Compare the arrays again. If they match, return `true`.
6. **Completion:** If the loop finishes scanning `s2` without finding a match, return `false`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of `s2`. We iterate through `s1` once and `s2` once. The `Arrays.equals()` check compares exactly 26 elements every time, which is an $O(1)$ constant time operation. Therefore, the overall time complexity remains strictly linear.
* **Space Complexity:** $O(1)$
  We allocate two integer arrays of size 26. Because this size never changes regardless of the input strings' lengths, the space complexity is constant.

## Java Solution

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // If s1 is larger than s2, s2 cannot contain a permutation of s1
        if(s1.length() > s2.length()){
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Populate the frequency arrays for s1 and the first window of s2
        for(int i = 0; i < s1.length(); i++){
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        // Check if the first window is a match
        if(Arrays.equals(count1, count2)){
            return true;
        }

        // Slide the window across the rest of s2
        for(int i = s1.length(); i < s2.length(); i++){
            // Add the new character entering the window
            count2[s2.charAt(i) - 'a']++;
            // Remove the character leaving the window
            count2[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window is a match
            if(Arrays.equals(count1, count2)){
                return true;
            }
        }

        return false;
    }
}
