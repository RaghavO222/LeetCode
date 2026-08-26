class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxL = 0;
        HashSet<Character> charSet = new HashSet<>();

        // The 'right' pointer expands the window
        for(int right = 0; right < s.length(); right++){
            
            // If a duplicate is found, shrink the window from the left
            while(charSet.contains(s.charAt(right))){
                charSet.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the set
            charSet.add(s.charAt(right));
            
            // Update the maximum length found so far
            maxL = Math.max(maxL, right - left + 1);
        }
        
        return maxL;        
    }
}
