class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        
        // Phase 1: Find the intersection point
        do {
            slow = nums[slow]; // Move 1 step
            fast = nums[nums[fast]]; // Move 2 steps
        } while(slow != fast);

        // Phase 2: Find the entrance to the cycle (the duplicate number)
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
