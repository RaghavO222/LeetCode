# 875. Koko Eating Bananas

**Difficulty:** Medium

## Problem Statement
Koko loves to eat bananas. There are `n` piles of bananas, the $i^{th}$ pile has `piles[i]` bananas. The guards have gone and will come back in `h` hours.

Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses some pile of bananas and eats `k` bananas from that pile. If the pile has less than `k` bananas, she eats all of them instead and will not eat any more bananas during this hour.

Return the minimum integer `k` such that she can eat all the bananas within `h` hours.

## Intuition & Approach
This is a classic "Binary Search on Answer" problem. We know the absolute minimum speed Koko can eat is `1` banana per hour. The maximum speed she would ever need is the size of the largest pile in the array (because she can only eat from one pile per hour anyway).

Instead of testing every single speed from `1` to `max`, we can binary search the speed!

1. **Find the Search Space:** Set `minSpeed = 1` and find the largest pile to be our `maxSpeed`.
2. **Binary Search:** Calculate a `mid` speed. Use a helper function `canEat` to simulate Koko eating at that speed.
    * In `canEat`, we calculate the total hours taken for each pile by dividing the pile size by the speed and rounding up (`Math.ceil`).
    * If `canEat` returns `true`, Koko *can* finish the bananas. This `mid` speed is a valid answer, but we want the *minimum*, so we record it and search the lower half (`maxSpeed = mid`).
    * If `canEat` returns `false`, she is eating too slowly. We must search the upper half (`minSpeed = mid + 1`).
3. **Completion:** The loop narrows down until `minSpeed` and `maxSpeed` converge on the exact minimum viable speed.

## Complexity Analysis

* **Time Complexity:** $O(N \log M)$
  Where $N$ is the number of piles and $M$ is the size of the maximum pile. The binary search takes $O(\log M)$ steps, and for each step, we iterate through all $N$ piles to calculate the hours.
* **Space Complexity:** $O(1)$
  No extra space is used besides a few integer variables.

## Java Solution

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;

        int maxSpeed = 0;
        for(int n: piles){
            maxSpeed = Math.max(n, maxSpeed);
        }

        while(minSpeed < maxSpeed){
            int mid = minSpeed + (maxSpeed - minSpeed) / 2;

            if(canEat(piles, h, mid)){
                maxSpeed = mid;
            }else{
                minSpeed = mid + 1;
            }
        }
        return minSpeed;
    }

    public boolean canEat(int[] piles, int h, int mid){
        int hours = 0;
        for(int n: piles){
            // Calculate hours needed for this pile and round up
            hours += (int)Math.ceil((double)n / mid);
        }

        return hours <= h;
    }
}
