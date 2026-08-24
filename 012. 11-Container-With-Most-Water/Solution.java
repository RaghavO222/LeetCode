class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;

        while(left < right){
            // Calculate current area and update max if needed
            max = Math.max(max, (right - left) * (Math.min(height[left], height[right])));

            // Move the pointer of the shorter line inward
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
