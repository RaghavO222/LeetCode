class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while(low <= high){
            // Calculate mid to avoid integer overflow
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                return mid; // Target found
            }
            else if(nums[mid] < target){
                // Target is in the right half, adjust lower bound
                low = mid + 1;
            }
            else{
                // Target is in the left half, adjust upper bound
                high = mid - 1;
            }
        }

        // Target not found in the array
        return -1;
    }
}
