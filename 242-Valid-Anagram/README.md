## 242. Valid Anagram

**Difficulty:** Easy
**Language:** Java

### Approach & What I Learned

**Approach 1: HashMaps (My Initial Solution)**
- You can count the frequency of each character in both strings using two HashMaps.
- **`HashMap.equals()`:** This method compares two maps by checking if they have the same size, and then verifying that every key-value pair in the first map exists exactly in the second map. Because there are at most 26 lowercase letters, this takes **O(1)** time.

**Approach 2: Fixed Array (Optimized Solution)**
- Because the problem only uses lowercase English letters, we can use a simple integer array of size 26.
- We map 'a' to index 0, 'b' to index 1, etc., using `s.charAt(i) - 'a'`.
- We increment the count for characters in string `s` and decrement for string `t`. If the strings are anagrams, every value in the array will balance out to `0` at the end.
- **Why it is faster:** It strictly uses primitive types and basic array indexing, completely avoiding the overhead of HashMap operations and object creation.

### Key Java Concepts Learned
- **Primitive vs. Object Types:** 
  - **Primitives** (e.g., `int`, `char`) store raw data directly in memory. They are lightweight and incredibly fast.
  - **Objects / Wrapper Classes** (e.g., `Integer`, `Character`) are heavier. They are stored in the heap, take up more memory, and require memory reference lookups. However, Java Collections like `HashMap` require them.
- **Autoboxing:** This is Java's automatic conversion of a primitive type into its corresponding object wrapper (e.g., converting an `int` to an `Integer` to put it inside a HashMap). Every time we updated a count in the HashMap solution, Java was secretly creating a new `Integer` object.
- **Unboxing:** The parallel concept to autoboxing. It is the automatic conversion of a wrapper object back into its primitive type (e.g., taking an `Integer` out of a HashMap and treating it as an `int`).
- *Takeaway:* The array approach is drastically faster because it skips autoboxing and unboxing entirely, manipulating raw primitives in continuous memory.

### Complexity
- **Time Complexity:** O(N) — We iterate through the strings of length N exactly once. Checking the array of size 26 at the end takes O(1) time.
- **Space Complexity:** O(1) — The array strictly takes 26 spaces, regardless of how massive the input strings are.

  ### Code (Optimized Array Approach)
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        for (int value : count) {
            if (value != 0) {
                return false;
            }
        }
        
        return true;
    }
}
