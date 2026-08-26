class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> freq = new HashMap<>();

        int maxF = 0; // Frequency of the most common character
        int res = 0;  // Maximum window size found so far

        int i = 0; // Left pointer

        // 'j' is the right pointer expanding the window
        for(int j = 0; j < s.length(); j++){
            // Add current character to frequency map
            freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0) + 1);
            
            // Update the historical maximum frequency
            maxF = Math.max(maxF, freq.get(s.charAt(j)));
            
            // If characters to replace exceed 'k', shrink the window
            while((j - i + 1) - maxF > k){
                freq.put(s.charAt(i), freq.get(s.charAt(i)) - 1);
                i++;
            }
            
            // Update the result with the current valid window size
            res = Math.max(res, j - i + 1);
        }
        
        return res;
    }
}
