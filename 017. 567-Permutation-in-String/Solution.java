class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // If s1 is larger than s2, s2 cannot contain a permutation of s1
        if(s1.length() > s2.length()){
            return false;
        }

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        // Populate the frequency arrays for s1 and the first window of s2
        for(int i = 0; i < s1.length(); i++){
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        // Check if the first window is a match
        if(Arrays.equals(count1, count2)){
            return true;
        }

        // Slide the window across the rest of s2
        for(int i = s1.length(); i < s2.length(); i++){
            // Add the new character entering the window
            count2[s2.charAt(i) - 'a']++;
            // Remove the character leaving the window
            count2[s2.charAt(i - s1.length()) - 'a']--;

            // Check if the current window is a match
            if(Arrays.equals(count1, count2)){
                return true;
            }
        }

        return false;
    }
}
