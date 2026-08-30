class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int[] res = new int[temp.length];
        
        // Stack stores the INDICES of days waiting for a warmer temperature
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < temp.length; i++){
            // While the current day is warmer than the day at the top of the stack
            while(!st.isEmpty() && temp[st.peek()] < temp[i]){
                // We found a warmer day! Calculate the distance in days.
                res[st.peek()] = i - st.pop();
            }
            
            // Push the current day's index onto the stack to wait for a warmer day
            st.push(i);
        }

        return res;
    }
}
