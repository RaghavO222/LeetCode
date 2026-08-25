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
