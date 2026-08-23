## 49. Group Anagrams

**Difficulty:** Medium
**Language:** Java

### Approach & What I Learned

**The Categorization Approach (Sorting)**
- The core logic here is finding a common "signature" for words that are anagrams. If you take the words `"eat"`, `"tea"`, and `"ate"` and sort their characters alphabetically, they all become exactly the same string: `"aet"`.
- We can use this sorted string as a **Key** in a `HashMap`. 
- The **Value** in the HashMap will be a `List` of the original strings that match that signature.

**Java Collections Tricks Learned**
- **Working with References:** When we do `map.get(str).add(s);`, we are fetching the memory reference to the ArrayList stored in the map and directly adding an item to it. We do not need to `.put()` the list back into the map because the list is modified in place.
- **Quick Conversions:** We can extract all the grouped lists using `map.values()` and immediately convert them into our final return type by passing them into an ArrayList constructor: `new ArrayList<>(map.values())`.

### Complexity
- **Time Complexity:** O(N * K log K) — Let `N` be the number of strings in the array, and `K` be the maximum length of a string. We loop through `N` strings. For each string, we sort it, which takes O(K log K) time. 
- **Space Complexity:** O(N * K) — In the worst-case scenario (where there are no anagrams at all), the HashMap will store every single string as a unique key, and all the original strings in the lists, taking up space proportional to all the text combined.

### Code
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : strs) {
            // Convert to char array to sort, creating our "signature"
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String str = new String(c);

            // If this signature hasn't been seen yet, create a new list for it
            if(!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }

            // Add the original string to the list matching this signature
            map.get(str).add(s);
        }

        // Extract all the inner lists and package them into an outer list
        return new ArrayList<>(map.values());
    }
}
