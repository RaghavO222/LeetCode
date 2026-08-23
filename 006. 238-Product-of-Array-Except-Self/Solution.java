class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // Initialize the array with 1s
        Arrays.fill(ans, 1);
        
        // PASS 1: Calculate left products
        int curr = 1;
        for (int i = 0; i < n; i++) {
            ans[i] *= curr;        // Store the product of everything to the left
            curr *= nums[i];       // Update the running product for the next index
        }
        
        // PASS 2: Calculate right products and multiply with left products
        curr = 1; // Reset running product for the backward pass
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;        // Multiply the left product by everything to the right
            curr *= nums[i];       // Update the running product for the next index
        }
        
        return ans;
    }
}
