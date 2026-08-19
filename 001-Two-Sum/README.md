## 1. Two Sum

**Difficulty:** Easy
**Language:** Java

### Approach & What I Learned

**The Brute Force Way (O(N²))**
- The most obvious way to solve this is to use two nested loops to check every single pair of numbers to see if they add up to the target. 
- However, this results in an **O(N²)** time complexity, which is very slow for large arrays.

**The Optimized Way: One-Pass Hash Table (My Solution)**
- Instead of checking every pair, we can use a little algebra: `current_number + needed_number = target` is the same as `target - current_number = needed_number`.
- As we iterate through the array, we calculate the `needed_number` (the complement) for the current element.
- We check if this complement already exists in our `HashMap`. 
  - If it does, we immediately have our answer: the current index and the index of the complement stored in the map.
  - If it doesn't, we add the current number and its index to the map and move on.
- **Why this is better:** By trading a little bit of memory to store the numbers we've already seen, we turn an O(N²) problem into a highly efficient **O(N)** solution.

### Complexity
- **Time Complexity:** O(N) — We iterate through the array at most once. Checking if a key exists in a HashMap takes O(1) time on average.
- **Space Complexity:** O(N) — In the worst-case scenario (where the pair is at the very end of the array), we will store almost all N elements in the HashMap.

### Code (Optimized One-Pass)
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> nMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int c = target - nums[i];
            
            if(nMap.containsKey(c)){
                return new int[] {nMap.get(c), i};
            }
            
            nMap.put(nums[i], i);
        }

        return new int[]{};
    }
}
