# 121. Best Time to Buy and Sell Stock

**Difficulty:** Easy

## Problem Statement
You are given an array `prices` where `prices[i]` is the price of a given stock on the $i^{th}$ day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. 

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

## Intuition & Approach
A naive approach would be to use two nested loops to check every possible pair of buying and selling days. However, that results in an $O(N^2)$ time complexity, which is too slow. We can solve this in a single pass using a **Greedy approach** (often conceptualized as a simplified Two-Pointer or Sliding Window technique).

The logic is straightforward: to maximize profit, we need to buy at the lowest possible price and sell at the highest possible price *after* that buy date.

1. **Initialize Trackers:** We set our initial buying price (`buy`) to the stock price on the first day (`prices[0]`). We also track our `maxProfit`, initializing it to `0`.
2. **Scan the Prices:** We iterate through the array starting from the second day.
3. **Find the Lowest Buy Price:** At each day, we check if the current price is lower than our established `buy` price. If it is, we update our `buy` variable. This ensures we are always holding onto the lowest possible entry point seen so far.
4. **Calculate Potential Profit:** If the current price is *not* lower than our buy price, we calculate the profit we would make if we sold today (`prices[i] - buy`). We then update our `maxProfit` if this current profit is greater than any profit we've recorded previously.
5. **Completion:** By the end of the array, `maxProfit` will hold the highest possible yield.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the length of the `prices` array. We only loop through the array exactly once, performing constant time $O(1)$ operations at each step.
* **Space Complexity:** $O(1)$
  We only allocate memory for two integer variables (`maxProfit` and `buy`), meaning our memory footprint remains constant regardless of how large the input array is.

## Java Solution

```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buy = prices[0]; // Assume we buy on the first day

        for(int i = 1; i < prices.length; i++){
            // If we find a lower price, update our buying price
            if(prices[i] < buy){
                buy = prices[i];
            }
            // Otherwise, check if selling today yields a better max profit
            if(prices[i] - buy > maxProfit){
                maxProfit = prices[i] - buy;
            }
        }

        return maxProfit;
    }
}
