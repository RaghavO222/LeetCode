# LeetCode 347: Top K Frequent Elements

## 📝 Problem Statement
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

**Example:**
- **Input:** `nums = [1,1,1,2,2,3]`, `k = 2`
- **Output:** `[1,2]`

---

## 💡 The Approach: Frequency Map + Min-Heap
To solve this efficiently, we don't need to sort the entire array. We just need to keep track of the top `k` "champions" as we go. We do this in two phases:
1. **Count the frequencies** using a `HashMap`.
2. **Filter the top `k` elements** using a `PriorityQueue` (Min-Heap).

### 🧠 Core Concepts Explained

#### 1. What is a Priority Queue?
Unlike a standard Queue (First-In, First-Out), a Priority Queue orders elements based on a specific "priority" rule. Think of it like a Hospital Emergency Room: patients aren't treated based on who arrived first, but rather on the severity of their condition. 

#### 2. What is a Min-Heap?
Behind the scenes, Java uses a data structure called a **Min-Heap** for its Priority Queue. It is a tree-like structure where the "smallest" element is always pushed to the very top. 
* By keeping the heap size strictly at `k`, we ensure the element at the top is always the *least frequent* of our top `k` champions. 
* When the heap gets too big, we just pop the top element off, instantly throwing away the weakest candidate.

#### 3. The Custom Comparator (Lambda Magic)
Because we want to sort the numbers by their *frequencies*, not their actual values, we have to teach Java how to compare them. We do this using a Lambda Expression:
```java
(a, b) -> map.get(a) - map.get(b)
```
#### How it works (The Golden Rule):
When Java compares two elements (a and b), it looks at the result of the subtraction:

Negative result: a is smaller, so a moves to the top of the Min-Heap.

Positive result: b is smaller, so b moves to the top of the Min-Heap.

Note: To create a Max-Heap instead, you simply reverse the math to map.get(b) - map.get(a), which tricks Java into pushing the larger values to the top!

#### ⏱️ Complexity AnalysisTime Complexity: 
$O(N \log k)$Building the frequency map takes 

$O(N)$ time.Adding elements to the heap takes 

$O(\log k)$ time, and we do this for at most $N$ unique elements.This is significantly faster than standard sorting, which would take $O(N \log N)$.Space Complexity: $O(N)$In the worst-case scenario (all numbers are unique), our HashMap will store $N$ key-value pairs. Our Priority Queue stores at most $k$ elements.


Code
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a,b) -> map.get(a) - map.get(b)
        );

        for(int key: map.keySet()){
            heap.add(key);
            if(heap.size() > k){
                heap.poll();
            }
        }



        int[] res = new int[k];
        for(int i = 0;i < k;i++){
            res[i] = heap.poll();
        }

        return res;
    }
}
```
