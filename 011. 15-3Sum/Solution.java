class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            // Step 4a: Skip duplicates for our fixed pointer 'i'
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            // Step 2 & 3: Set up Two Pointers
            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > 0){
                    k--;
                } else if(sum < 0){
                    j++;
                } else {
                    // Match found! Add it to our list
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    // Step 4b: Skip duplicates for our left pointer 'j'
                    while(nums[j] == nums[j - 1] && j < k){
                        j++;
                    }
                }
            }
        }
        
        return list;
    }
}
