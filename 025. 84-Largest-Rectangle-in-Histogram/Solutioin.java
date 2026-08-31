class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        // Arrays to store the nearest smaller bar's index on the left and right
        int[] left = new int[n];
        int[] right = new int[n];
        
        // Stack stores indices of the histogram bars
        Stack<Integer> st = new Stack<>();

        // 1. Find the Next Smaller Element to the left
        for(int i = 0; i < n; i++){
            // Maintain monotonic increasing order
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            // If stack is empty, boundary is -1. Otherwise, it's the top element.
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        // Clear the stack to reuse it for the right side
        st.clear();

        // 2. Find the Next Smaller Element to the right
        for(int i = n - 1; i >= 0; i--){
            // Maintain monotonic increasing order
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            // If stack is empty, boundary is n (out of bounds on the right)
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        // 3. Calculate max area for each bar
        int res = 0;
        for(int i = 0; i < n; i++){
            // Width is the distance between the right and left smaller bars
            int width = right[i] - left[i] - 1;
            res = Math.max(res, heights[i] * width);
        }
        
        return res;
    }
}