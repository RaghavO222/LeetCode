class MinStack {

    // Stack stores integer arrays: [value, current_minimum]
    private Stack<int[]> st;

    public MinStack() {
        st = new Stack<>();
    }
    
    public void push(int value) {
        if(st.isEmpty()){
            // If empty, the value is the minimum
            st.push(new int[]{value, value});
        } else {
            // Find the smaller of the new value and the previous minimum
            int currMin = st.peek()[1];
            st.push(new int[]{value, Math.min(value, currMin)});
        }
    }
    
    public void pop() {
        st.pop();
    }
    
    public int top() {
        // Return the actual value
        return st.peek()[0];
    }
    
    public int getMin() {
        // Return the minimum associated with the top element
        return st.peek()[1];
    }
}
