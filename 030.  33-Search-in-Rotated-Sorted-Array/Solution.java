class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                return mid;
            }

            // Check if the left portion is sorted
            if(nums[low] <= nums[mid]){
                // Check if target is inside the sorted left portion
                if(nums[low] <= target && target < nums[mid]){
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Otherwise, the right portion must be sorted
            else {
                // Check if target is inside the sorted right portion
                if(nums[mid] < target && target <= nums[high]){
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }   
        return -1;
    }
}
