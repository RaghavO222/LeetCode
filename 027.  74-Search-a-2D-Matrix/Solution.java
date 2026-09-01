class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Set boundaries for the virtual 1D array
        int low = 0;
        int high = m * n - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            
            // Map the 1D 'mid' index back to 2D matrix coordinates
            int val = matrix[mid / n][mid % n];

            if(val == target){
                return true;
            } else if(val > target){
                // Target is in the left half
                high = mid - 1;
            } else{
                // Target is in the right half
                low = mid + 1;
            }
        }

        return false;
    }
}
