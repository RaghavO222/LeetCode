class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> nMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int c = target - nums[i];
            
            if(nMap.containsKey(c)){
                return new int[] {nMap.get(c), i};
            }
            
            nMap.put(nums[i], i);
        }

        return new int[]{};
    }
}
