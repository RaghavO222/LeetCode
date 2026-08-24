class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int max = 0; // Acts as our total trapped water accumulator

        int left = 0;  // Max height seen from the left
        int right = 0; // Max height seen from the right

        int start = 0;
        int end = n - 1;

        while(start < end){
            // Update the maximum heights seen so far
            left = Math.max(left, height[start]);
            right = Math.max(right, height[end]);

            // If the left boundary is smaller, it dictates the water level for the 'start' pointer
            if(left < right){
                max += left - height[start];
                start++;
            } 
            // Otherwise, the right boundary dictates the water level for the 'end' pointer
            else {
                max += right - height[end];
                end--;
            }
        }

        return max;
    }
}
