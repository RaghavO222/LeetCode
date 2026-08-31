# 853. Car Fleet

**Difficulty:** Medium

## Problem Statement
There are `n` cars going to the same destination along a one-lane road. The destination is `target` miles away.

You are given two integer arrays `position` and `speed`, both of length `n`, where `position[i]` is the position of the $i^{th}$ car and `speed[i]` is the speed of the $i^{th}$ car (in miles per hour).

A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed. A car fleet is some non-empty set of cars driving at the same position and same speed. A single car is also a car fleet.

Return the number of car fleets that will arrive at the destination.

## Intuition & Approach
The core physics of this problem is simple: `Time = Distance / Speed`. 
If a car starts *behind* another car but takes *less time* to reach the target, it is guaranteed to catch up and form a fleet with that slower car.

1. **Calculate Arrival Times:** First, we pair each car's starting position with the time it would take to reach the target if nothing was in its way: `(target - position) / speed`.
2. **Sort by Position:** We sort the cars based on their starting positions in **descending order** (from closest to the target to furthest). We evaluate cars closest to the target first because they act as the "bottlenecks" for the cars behind them.
3. **Count the Fleets:** We iterate through the sorted cars and track the time it took the *previous* fleet to arrive (`prevTime`).
    * If the current car's time is **greater** than `prevTime`, it means it is too slow to catch up to the fleet ahead of it. Therefore, it becomes the leader of a brand-new fleet. We increment our fleet `count` and update `prevTime` to this car's slower time.
    * If the current car's time is **less than or equal** to `prevTime`, it is fast enough to catch up to the fleet ahead. It joins that fleet, and because it slows down to match them, the fleet's arrival time (`prevTime`) does not change. We do nothing but continue the loop.

## Complexity Analysis

* **Time Complexity:** O(N log N)
  Where N is the number of cars. Calculating the times and iterating through the array takes O(N) time, but sorting the 2D array dominates the time complexity, requiring O(N log N).
* **Space Complexity:** O(N)
  We create a 2D array of size `N x 2` to pair the positions and times together so we can sort them. However, by using a simple `prevTime` variable instead of a `Stack` to track fleets during the iteration, we avoid allocating any additional memory beyond the initial array setup.

## Java Solution

```java
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        
        // Array to store [position, time_to_target]
        double[][] cars = new double[n][2];

        // Calculate the time it takes for each car to reach the target
        for(int i = 0; i < n; i++){
            cars[i][0] = position[i];
            cars[i][1] = (double)(target - position[i]) / speed[i];
        }

        // Sort cars by starting position in descending order (closest to target first)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        int count = 0;
        double prevTime = 0;
        
        // Iterate through the sorted cars
        for(double[] car: cars){
            // If the current car takes longer than the fleet ahead, it forms a new fleet
            if(car[1] > prevTime){
                count++;
                prevTime = car[1]; // Update the bottleneck time for the cars behind
            }
        }

        return count;
    }
}