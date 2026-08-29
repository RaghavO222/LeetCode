class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] res = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i<k; i++){
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }

        res[0] = nums[dq.peekFirst()];

        for(int i = k; i<n; i++){

            if(dq.peekFirst() <= i-k){
                dq.pollFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i] ){
                dq.pollLast();
            }

            dq.offerLast(i);

            res[i-k+1] = nums[dq.peekFirst()];
        }

        return res;

    }
}
