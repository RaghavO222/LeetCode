class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        int res = nums[low + (high - low) / 2];

        while(low <= high){
            int mid = low + (high - low) / 2;
            
            // Update our minimum tracker
            res = Math.min(res, nums[mid]);

            // If mid is greater than the rightmost element, the min is to the right
            if(nums[mid] > nums[high]){
                low = mid + 1;
            } else {
                // Otherwise, the min is to the left (or is mid itself)
                high = mid - 1;
            }
        }

        return res;
    }
}
