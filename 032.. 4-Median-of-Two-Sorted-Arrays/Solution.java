class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure we always binary search on the smaller array
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        
        int totalLength = nums1.length + nums2.length;
        int low = 0;
        int high = smaller.length;

        while(low <= high){
            int partitionX = (low + high) / 2;
            int partitionY = (totalLength + 1) / 2 - partitionX;

            // Handle edge cases where partition is at the extreme ends
            int l1 = partitionX == 0 ? Integer.MIN_VALUE : smaller[partitionX - 1];
            int r1 = partitionX == smaller.length ? Integer.MAX_VALUE : smaller[partitionX];

            int l2 = partitionY == 0 ? Integer.MIN_VALUE : larger[partitionY - 1];
            int r2 = partitionY == larger.length ? Integer.MAX_VALUE : larger[partitionY];

            // Valid partition found
            if(l1 <= r2 && l2 <= r1){
                if((totalLength) % 2 == 0){
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            }
            
            // Adjust partition on the smaller array
            if(l1 > r2){
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        
        return 0;        
    }
}
