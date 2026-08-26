# 424. Longest Repeating Character Replacement

**Difficulty:** Medium

## Problem Statement
You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Intuition & Approach
We can solve this using the **Sliding Window technique** combined with a frequency map. 

The core logic revolves around this formula:  
`Window Size - Count of Most Frequent Character = Characters to Replace`

If the number of characters we need to replace is less than or equal to `k`, our window is valid. If it exceeds `k`, the window is invalid and we must shrink it.

1. **Initialize Variables:** We use two pointers, `i` (left) and `j` (right), to define our window. We use a `HashMap` to track the frequency of each character inside the current window. We also track `maxF` (the frequency of the most common character) and `res` (our maximum valid window size).
2. **Expand the Window:** As the `j` pointer moves right, we add the new character to our frequency map and update `maxF` if this character's new count is the highest we've seen.
3. **Validate the Window:** We calculate the number of characters that need replacing: `(j - i + 1) - maxF`. 
4. **Shrink if Invalid:** If the characters to replace exceed `k`, the current window is invalid. We shrink it from the left by decrementing the frequency of the character at `s.charAt(i)` and moving the `i` pointer forward.
5. **The `maxF` Trick:** Notice that when we shrink the window, we *do not* rescan the map to find a potentially lower `maxF`. Why? Because we only care about finding a window *larger* than our current maximum (`res`). To get a larger window, we would mathematically need a higher `maxF`. Therefore, keeping `maxF` at its historical maximum is safe and saves us from an $O(26)$ scan during every shrink step!
6. **Record Maximum:** Update `res` with the valid window's size.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the string. Both the `i` and `j` pointers only move forward, visiting each character at most twice. Updating and reading from the `HashMap` takes $O(1)$ time.
* **Space Complexity:** $O(1)$
  Although we use a `HashMap`, the problem states the string consists of only uppercase English letters. The map will store a maximum of 26 key-value pairs, which is a constant amount of extra space.

## Java Solution

```java
class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();

        int maxF = 0; // Frequency of the most common character
        int res = 0;  // Maximum window size found so far

        int i = 0; // Left pointer

        // 'j' is the right pointer expanding the window
        for(int j = 0; j < s.length(); j++){
            // Add current character to frequency map
            freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);
            
            // Update the historical maximum frequency
            maxF = Math.max(maxF, freq.get(s.charAt(j)));
            
            // If characters to replace exceed 'k', shrink the window
            while((j - i + 1) - maxF > k){
                freq.put(s.charAt(i), freq.get(s.charAt(i)) - 1);
                i++;
            }
            
            // Update the result with the current valid window size
            res = Math.max(res, j - i + 1);
        }
        
        return res;
    }
}
