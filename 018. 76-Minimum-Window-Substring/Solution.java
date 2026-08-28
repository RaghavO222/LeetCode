class Solution {
    public String minWindow(String s, String t) {
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        // Populate required frequencies from string t
        for(char ch: t.toCharArray()){
            mapT[ch]++;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        // 'right' pointer expands the window
        for(int right = 0; right < s.length(); right++){
            mapS[s.charAt(right)]++;

            // While the window contains all characters of t, try to shrink it
            while(contain(mapS, mapT)){
                
                // Record the new minimum window
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    minStart = left;
                }
                
                // Shrink from the left
                mapS[s.charAt(left)]--;
                left++;
            }
        }

        // If minLen wasn't changed, no valid window was found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // Helper method to check if mapS contains all frequencies required by mapT
    public boolean contain(int[] mapS, int[] mapT){
        for(int i = 0; i < 256; i++){
            if(mapT[i] > mapS[i]){
                return false;
            }
        }
        return true;
    }
}
