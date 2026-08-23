class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while(left < right){
            // Target found
            if(numbers[left] + numbers[right] == target){
                return new int[]{left + 1, right + 1};
            }

            // Sum is too small, move left pointer to increase sum
            if(numbers[left] + numbers[right] < target){
                left++;
            }

            // Sum is too large, move right pointer to decrease sum
            if(numbers[left] + numbers[right] > target){
                right--;
            }
        }   

        // Fallback (Problem guarantees a valid answer exists)
        return new int[]{};
    }
}
