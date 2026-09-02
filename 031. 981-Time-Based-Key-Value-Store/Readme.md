# 981. Time Based Key-Value Store

**Difficulty:** Medium

## Problem Statement
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the `TimeMap` class with `set` and `get` methods. The `get` method should return the value associated with the largest timestamp that is less than or equal to the requested `timestamp`.

## Intuition & Approach
We need to map a `String` key to a collection of values and their timestamps. Because the problem guarantees that all `set` operations are called with strictly increasing timestamps, any list we append these objects to will **naturally be sorted by timestamp**. 

Because the list is already sorted, we can use Binary Search to quickly find the largest timestamp that satisfies the condition for the `get` method.

1. **Data Structure:** We use a `HashMap` where the key is the string `key`, and the value is an `ArrayList` of custom `TimeStampedValue` objects.
2. **Set Operation:** Simply grab the list for the given key (or create a new one) and append the new `TimeStampedValue`. This takes $O(1)$ time.
3. **Get Operation:** We fetch the list for the given key. If it doesn't exist, return `""`. If it does, we run a binary search on the list.
    * We look for a `timestamp` $\le$ `target`. 
    * If `cur.time <= target`, this is a valid candidate! We record its index in `res` and move `low = mid + 1` to see if we can find an even larger valid timestamp.
    * If `cur.time > target`, it's too large, so we move `high = mid - 1`.

## Complexity Analysis

* **Time Complexity:** 
  * `set()`: $O(1)$ amortized time to append to an `ArrayList`.
  * `get()`: $O(\log N)$ where $N$ is the number of entries for a specific key.
* **Space Complexity:** $O(K \times N)$
  Where $K$ is the number of unique keys and $N$ is the average number of values per key. We store an object for every single `set` operation.

## Java Solution

```java
class TimeMap {

    class TimeStampedValue{
        public String value;
        public int time;

        public TimeStampedValue(String value, int time){
            this.value = value;
            this.time = time;
        }
    }

    Map<String, ArrayList<TimeStampedValue>> entries;

    public TimeMap() {
        entries = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!entries.containsKey(key)){
            entries.put(key, new ArrayList<>());
        }
        ArrayList<TimeStampedValue> tSV = entries.get(key);
        tSV.add(new TimeStampedValue(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(!entries.containsKey(key)){
            return "";
        }

        ArrayList<TimeStampedValue> timeStampedValues = entries.get(key);
        Optional<TimeStampedValue> resStamp = binaryStampSearch(timeStampedValues, timestamp);

        if(resStamp.isEmpty()){
            return "";
        }
        return resStamp.get().value;
    }

    public Optional<TimeStampedValue> binaryStampSearch(ArrayList<TimeStampedValue> arr, int target){
        int low = 0;
        int high = arr.size() - 1;
        int res = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            TimeStampedValue cur = arr.get(mid);
            
            // Valid candidate found, record it and search for a closer one
            if(cur.time <= target){
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if(res == -1){
            return Optional.empty();
        }
        return Optional.of(arr.get(res));
    }
}
